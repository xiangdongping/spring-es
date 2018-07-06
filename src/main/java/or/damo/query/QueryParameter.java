package or.damo.query;

import lombok.Data;
import or.damo.query.enums.SortType;

/**
 * @author xdp
 * @date 2018/7/4
 */
@Data
public class QueryParameter {

    private  Integer priceBegin;

    private Integer priceBegn;

    private String searchContent;

    private SortType sortType;
}
