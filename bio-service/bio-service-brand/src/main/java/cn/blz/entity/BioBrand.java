package cn.blz.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * bio_brand 商品品牌(BioBrand)实体类
 *
 * @author blz
 * @since 2019-02-08 23:51:18
 */

@Data
@TableName("bio_brand")
public class BioBrand implements Serializable {
    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 商品品牌名称
     */
    @TableField
    private String name;
    /**
     * 商品品图片
     */
    @TableField
    private String img;
    /**
     * 商品排序
     */
    @TableField
    private Integer sort;
}
