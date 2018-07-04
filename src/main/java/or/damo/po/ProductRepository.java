package or.damo.po;

import or.damo.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author xdp
 * @date 2018/6/27
 */
public interface ProductRepository  extends ElasticsearchRepository<Product,Integer>{

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
