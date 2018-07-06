package or.damo.service.impl;

import lombok.extern.slf4j.Slf4j;
import or.damo.entity.Product;
import or.damo.es.ProductESRepository;
import or.damo.service.ProductService;
import or.damo.service.lock.Retry;
import org.apache.http.util.Asserts;
import org.elasticsearch.index.engine.VersionConflictEngineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author xdp
 * @date 2018/7/4
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Resource(name="product-id")
    private RedisAtomicLong redisAtomicLong;

    @Autowired
    private ProductESRepository productRepository;
    @Override
    public Boolean updateStock(Integer productId,Integer num) {
        Asserts.notNull(productId,"id");


        Product product = getById(productId);


        product.setVersion(product.getVersion()+1);
        product.setStock(num);

        productRepository.save(product);
        return Boolean.TRUE;
    }

    private Product getById(Integer productId){
        Optional<Product> ori= productRepository.findById(productId);
        ori.orElseThrow( RuntimeException::new);
        Product product = ori.get();

        return ori.get();
    }

    @Override
    public Boolean subStock(Integer productId, Integer num) {
        Product product = getById(productId);



        doUpdate(product,pro ->{
            int remain= pro.getStock().intValue() - num;
            //库存不足
            if(remain < -0){
                throw new RuntimeException("stock insufficient");
            }

            pro.setVersion(pro.getVersion().longValue()+ 1);
            log.info("product {} stock {} remain {}",pro.getName(),pro.getStock(),remain,pro.getVersion());
            pro.setStock(remain);

            return  pro;
        });
        return true;
    }

    /**
     * 乐观锁控制重试。
     * @param product
     * @param retry
     */
    public  void doUpdate(Product product, Retry<Product> retry){
        Product pro = retry.retry(product);
        try {
            productRepository.save(pro);
        }catch (VersionConflictEngineException e){
            log.warn("lock conflict retry" ,pro.getVersion());
            pro=getById(pro.getId());
            doUpdate(pro,retry);
        }


    }

    @Override
    public Integer getStock(Integer stock) {
        return null;
    }

    @Override
    public Boolean save(Product product) {
        product.setId((int)redisAtomicLong.getAndIncrement());

        productRepository.save(product);

        return true;
    }

    @Override
    public void updateProduct(Product product) {
        doUpdate(product,pro ->{
            pro.setVersion(pro.getVersion()+1);
            return  pro;
        });
    }
}
