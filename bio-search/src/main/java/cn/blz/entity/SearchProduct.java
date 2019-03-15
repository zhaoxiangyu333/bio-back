package cn.blz.entity;

import java.util.Date;
import java.io.Serializable;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author blz
 * @since 2019-02-25 20:21:19
 */

@Data
@Document(indexName = "bio", type = "product", shards = 1, replicas = 0)
public class SearchProduct implements Serializable {
    /**
     * id
     */
    @Id
    private Integer id;
    /**
     * 商品名称
     */
    @Field(type = FieldType.text, analyzer = "ik_max_word")
    private String name;
    /**
     * 商品分类id
     */
    private Integer categoryId;
    /**
     * 商品品牌id
     */
    private Integer brandId;
    /**
     * 商品描述
     */
    private String intro;
    /**
     * 商品关键字
     */
    @Field(type = FieldType.keyword)
    private String keywords;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新时间
     */
    private Date updatedTime;
}
