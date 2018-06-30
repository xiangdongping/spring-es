package or.damo.po;

import or.damo.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author xdp
 * @date 2018/6/27
 */
public interface ProductRepository  extends ElasticsearchRepository<Product,Integer>{

}
