package or.damo.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.Date;

/**
 * Created by xdp on 2018/6/25.
 */
@Document(indexName = "product_db",type = "product")
@Data
public class Product {
    @Field(index = false)
    private Integer id;

    /**
     * 产品名
     * -- ik_smart 是 ik 支持中文分词的插件参数 表示分词时尽可能 往细粒度分
     * -- 如 中国人名共和国歌， 中国，中，国，中国，人名，国歌..
     */
    @Field(index = true,analyzer = "ik_max_word",searchAnalyzer = "ik")
    private String name;

    /**
     * 价格
     */
    @Field(index = false)
    private Long price;

    /**
     * 说明
     * -- ik_smart 是 ik 支持中文分词的插件参数 表示分词时尽可能 向大来分
     * -- 如 中国人名共和国歌， 中国人名共和国，国歌
     */
    @Field(index = true,analyzer = "ik_smart",searchAnalyzer = "ik")
    private String summary;

    /**
     * 排序
     */
    @Field(index = false)
    private Integer sort;

    /**
     * 库存
     */
    @Field(index = false)
    private Integer stock;

    /**
     * 上架时间
     */
    @Field(index = false,format = DateFormat.year_month_day)
    private Date shelfDate;

    /**
     * 状态
     */
    @Field(index = false)
    private Integer status;


}
