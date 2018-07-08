package or.damo.service;

import or.damo.controller.SearchParameter;
import or.damo.entity.Commodity;
import org.springframework.data.domain.Page;

/**
 * Created by xdp on 2018/7/8.
 */
public interface CommodityService {

    /**
     * 根據id 查詢
     * @return
     */
    Commodity findById(Long id);

    /**
     * 搜索
     * @param searchParameter
     * @return
     */
    Page<Commodity> search(SearchParameter searchParameter);
}

