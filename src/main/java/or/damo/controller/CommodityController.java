package or.damo.controller;

import or.damo.entity.Commodity;
import or.damo.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by xdp on 2018/7/8.
 */
@RestController
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @RequestMapping("/{id}")
    public Commodity get(@PathVariable("id") Long id){
        return  null;
    }

    @RequestMapping("/search")
    public Page<Commodity> getList(@Valid SearchParameter searchParameter){
        Page<Commodity> search = commodityService.search(searchParameter);
        return  search;
    }

}
