package or.damo.controller;

import or.damo.entity.Product;
import or.damo.es.ProductESRepository;
import or.damo.service.ProductService;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author xdp
 * @date 2018/6/27
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductESRepository productRepository;

    @Autowired
    private ProductService productService;

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
        PageRequest of = PageRequest.of(page.getPage(), page.getSize(),new Sort(Sort.Direction.DESC,"shelDateLong"));
        Page<Product> search = productRepository.search(queryBuilder,of);

        search.forEach(e -> {
            e.setShelfDate(new Date(e.getShelDateLong()));
        });
        return  search;
    }

    @GetMapping("/stock/update")
    public Boolean updateStock(@RequestParam("productId") Integer productId,@RequestParam("stock") Integer stock){
        Boolean result = productService.updateStock(productId, stock);
        return  result;
    }

    @GetMapping("/stock")
    public Integer stock(@RequestParam("productId") Integer productId){
        Integer stock = productService.getStock(productId);
        return stock;
    }
    @GetMapping ("stock/sub")
    public Boolean subStock(@RequestParam("productId") Integer productId, @RequestParam("num")Integer num){
        Boolean aBoolean = productService.subStock(productId, num);

        return  aBoolean;
    }
}
