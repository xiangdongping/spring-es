package or.damo.vo;

import lombok.Data;
import lombok.ToString;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.Optional;

/**
 * @author xdp
 * @date 2018/7/5
 */
@Data
@ToString
public class ProductVo {

    
    private Integer id;

    /**
     * 产品名
     * -- ik_smart 是 ik 支持中文分词的插件参数 表示分词时尽可能 往细粒度分
     * -- 如 中国人名共和国歌， 中国，中，国，中国，人名，国歌..
     */
    
    private String name;

    /**
     * 价格
     */
    
    private Long price;

    /**
     * 说明
     * -- ik_smart 是 ik 支持中文分词的插件参数 表示分词时尽可能 向大来分
     * -- 如 中国人名共和国歌， 中国人名共和国，国歌
     */
    
    private String summary;

    /**
     * 排序
     */
    
    private Integer sort;

    /**
     * 库存
     */
    
    private Integer stock;

    /**
     * 时间
     */
    private Date shelfDate;

}
