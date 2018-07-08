package or.damo.service.impl;

import or.damo.builder.SearchBuilders;
import or.damo.controller.SearchParameter;
import or.damo.entity.Commodity;
import or.damo.es.CommodityESRepository;
import or.damo.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by xdp on 2018/7/8.
 */
@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityESRepository commodityESRepository;

    @Override
    public Commodity findById(Long id) {

        Optional<Commodity> commodity = commodityESRepository.findById(id);
        return  commodity.orElseGet(Commodity::new);
    }

    @Override
    public Page<Commodity> search(SearchParameter searchParameter) {

        SearchQuery searchQuery= SearchBuilders.build(searchParameter);

        Page<Commodity> search = commodityESRepository.search(searchQuery);
        return search;
    }
}
