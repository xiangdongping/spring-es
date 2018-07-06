package or.damo.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


import java.io.File;
import java.util.Date;

/**
 * @author xdp
 * @date 2018/7/6
 */
@Document(indexName = "product-index",type = "commodity")
@Data
@ToString
public class Commodity {
    @Field(type = FieldType.Long)
    @Id
    private Long id;

    @Field(type= FieldType.Text,analyzer = "ik_smart",searchAnalyzer = "ik_smart")
    private String title;

    @Field(type=FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String sellPoint;

    @Field(type=FieldType.Long)
    private Long price;

    @Field(type = FieldType.Integer)
    private  Integer stock;

    @Field(index = false,type=FieldType.Text)
    private String image;

    @Field(type=FieldType.Integer)
    private Integer status;

    @Field(type = FieldType.Long,index = false)
    private Date createTime;

    @Field(type=FieldType.Long,index = false)
    private Date updateTime;

    @Field(type = FieldType.Keyword)
    private String categoryName;

    @Field(type = FieldType.Integer)
    private  Integer sortOrder;

    @Field(type = FieldType.Text,analyzer = "ik_smart",searchAnalyzer = "ik_smart")
    private String desc;

    @Version
    private Long version;

}
