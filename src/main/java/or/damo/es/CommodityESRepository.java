package or.damo.es;

import or.damo.entity.Commodity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author xdp
 * @date 2018/7/6
 */
public interface CommodityESRepository extends ElasticsearchRepository<Commodity,Long> {

}
