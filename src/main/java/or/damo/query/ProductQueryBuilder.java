package or.damo.query;

import lombok.Getter;
import org.elasticsearch.index.query.QueryBuilder;

/**
 * @TODO 需要完成查询
 * @author xdp
 * @date 2018/7/4
 */
@Getter
public class ProductQueryBuilder {

    private QueryParameter queryParameter;

    private QueryBuilder queryBuilder;
    public ProductQueryBuilder(QueryParameter queryParameter) {

        this.queryParameter = queryParameter;
    }

    public  static  ProductQueryBuilder builder(QueryParameter queryParameter){
        return  new ProductQueryBuilder(queryParameter);
    }


    public ProductQueryBuilder priceBetween(){
        return  null;
    }
}
