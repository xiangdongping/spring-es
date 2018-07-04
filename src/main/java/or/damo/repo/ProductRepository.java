package or.damo.repo;

import or.damo.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by xdp on 2018/6/26.
 * @author xdp
 */
public interface ProductEsRepository extends ElasticsearchRepository<Product,Integer>{
}
