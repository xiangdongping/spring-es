package or.damo.controller;

import or.damo.entity.Product;
import or.damo.po.ProductRepository;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author xdp
 * @date 2018/6/27
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public Boolean save(@RequestBody Product product){

        /**
         * 为了演示方便才用这种方式设置主键，实际生产环境不要这么做
         */
        long count = productRepository.count();
        count++;

        product.setId((int)count);

        product.setShelDateLong(product.getShelfDate().getTime());
        productRepository.save(product);


        return  Boolean.TRUE;
    }

    @GetMapping("/search/{name}")
    public Page<Product> searchByName(@PathVariable("name") String name,BasePage page){
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("name", name);
        PageRequest of = PageRequest.of(page.getPage(), page.getSize(),Sort.Direction.DESC);
        Page<Product> search = productRepository.search(queryBuilder,of);

        search.forEach(e -> {
            e.setShelfDate(new Date(e.getShelDateLong()));
        });
        return  search;
    }

}
