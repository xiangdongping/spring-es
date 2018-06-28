package or.damo.entity;

import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

/**
 * Created by xdp on 2018/6/25.
 * @author  xdp
 */
@Document(indexName = "product-index",type = "product")
@Data
public class Product {
    @Field(type = FieldType.Integer)
    private Integer id;

    /**
     * 产品名
     * -- ik_smart 是 ik 支持中文分词的插件参数 表示分词时尽可能 往细粒度分
     * -- 如 中国人名共和国歌， 中国，中，国，中国，人名，国歌..
     */
    @Field(analyzer = "ik_max_word",searchAnalyzer = "ik_max_word",type=FieldType.Text)
    private String name;

    /**
     * 价格
     */
    @Field(type = FieldType.Long)
    private Long price;

    /**
     * 说明
     * -- ik_smart 是 ik 支持中文分词的插件参数 表示分词时尽可能 向大来分
     * -- 如 中国人名共和国歌， 中国人名共和国，国歌
     */
    @Field(analyzer = "ik_smart",searchAnalyzer = "ik_smart",type = FieldType.Text)
    private String summary;

    /**
     * 排序
     */
    @Field(index = false,type=FieldType.Integer)
    private Integer sort;

    /**
     * 库存
     */
    @Field(index = false,type=FieldType.Integer)
    private Integer stock;

    /**
     * 上架时间
     */
    @Transient
//   @fixme 此处有bug  @Field(type = FieldType.Date,format = DateFormat.year_month_day)
    private Date shelfDate;


    @Field(type = FieldType.Long)
    private Long shelDateLong;

    /**
     * 状态
     */
    @Field(index = false,type=FieldType.Integer)
    private Integer status;


    @Field(type = FieldType.Nested)
    private List<Comment> comments;

}
