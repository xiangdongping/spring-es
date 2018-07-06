package or.damo;

import or.damo.entity.Product;
import static  org.elasticsearch.index.query.QueryBuilders.*;

import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

/**
 * @author xdp
 * @date 2018/7/6
 */

public class SearchTest extends  SpringEsApplicationTests{

    @Test
    public void boolSearch(){

        BoolQueryBuilder bool = QueryBuilders.boolQuery()
                .must(QueryBuilders.multiMatchQuery("name", "iphone"))
                .should(QueryBuilders.matchQuery("summary", "手机"));

        BoolQueryBuilder filter= QueryBuilders.boolQuery();
        TermQueryBuilder status = QueryBuilders.termQuery("status", 1);
        RangeQueryBuilder stock = QueryBuilders.rangeQuery("stock").gt(0);

        filter.must(status);
        filter.must(stock);

        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(bool);
        queryBuilder.withFilter(filter);
        Page<Product> products = esRepository.search(queryBuilder.build());

        products.forEach(System.out::print);
    }

}
