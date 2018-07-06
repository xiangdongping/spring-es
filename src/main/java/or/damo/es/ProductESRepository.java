package or.damo.es;

import or.damo.entity.Product;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author xdp
 * @date 2018/6/27
 */
public interface ProductESRepository extends ElasticsearchRepository<Product,Integer>{

    /**
     * 改jpa 查询会被转化成 dsl 语句
     * 可能转换 {"query":{"bool":{"match":"name":"?name"}}}
     * @param name
     * @param begin
     * @param end
     * @return
     */
    List<Product> findByNameLikeAndShelDateLongBetween(String name,Long begin,Long end);
}
