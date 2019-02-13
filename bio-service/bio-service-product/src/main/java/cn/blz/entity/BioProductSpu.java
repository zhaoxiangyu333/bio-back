package cn.blz.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * bio_product_spu 商品表(spu)(BioProductSpu)实体类
 *
 * @author blz
 * @since 2019-02-08 23:36:58
 */

@Data
@TableName("bio_product_spu")
public class BioProductSpu implements Serializable {
    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 商品名称
     */
    @TableField
    private String name;
    /**
     * 商品分类id
     */
    @TableField
    private Integer categoryId;
    /**
     * 商品品牌id
     */
    @TableField
    private String brandId;
    /**
     * 商品描述
     */
    @TableField
    private String intro;
    /**
     * 商品关键字
     */
    @TableField
    private String keywords;
    /**
     * 创建时间
     */
    @TableField
    private Date createdTime;
    /**
     * 更新时间
     */
    @TableField
    private String updatedTime;
}
