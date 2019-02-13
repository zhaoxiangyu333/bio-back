package cn.blz.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * bio_category 商品分类表(BioCategory)实体类
 *
 * @author blz
 * @since 2019-02-08 23:46:19
 */

@Data
@TableName("bioCategory") 
public class BioCategory implements Serializable {
    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 商品分类名称
     */
    @TableField
    private String categoryName;
    /**
     * 商品分类父id
     */
    @TableField
    private Integer categoryParentId;
    /**
     * 商品分类是否为父类 0不是父类---1是父类
     */
    @TableField
    private Integer categoryIsParent;
}