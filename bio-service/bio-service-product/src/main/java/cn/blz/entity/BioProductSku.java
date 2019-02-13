package cn.blz.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * bio_product_sku 商品sku表(BioProductSku)实体类
 *
 * @author blz
 * @since 2019-02-11 21:30:42
 */

@Data
@TableName("bio_product_sku")
public class BioProductSku implements Serializable {
    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 商品spu_id
     */
    @TableField
    private Integer spuId;
    /**
     * 商品名称
     */
    @TableField
    private String name;
    /**
     * 商品图片
     */
    @TableField
    private String img;
    /**
     * 商品价格
     */
    @TableField
    private Double price;
    /**
     * 市场价格
     */
    @TableField
    private Double marketPrice;
    /**
     * 商品库存
     */
    @TableField
    private Integer stock;
    /**
     * 商品规格属性 通过_拼接各个详细属性
     */
    @TableField
    private String specificate;
    /**
     * 是否有效 0无效，1有效
     */
    @TableField
    private Integer enable;
    /**
     * 商品排序
     */
    @TableField
    private Integer sort;
    /**
     * 创建时间
     */
    @TableField
    private Date createdTime;
    /**
     * 更新时间
     */
    @TableField
    private Date updateTime;
}
