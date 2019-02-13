package cn.blz.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * bio_specificate_value 商品属性值表(BioSpecificateValue)实体类
 *
 * @author blz
 * @since 2019-02-08 13:37:05
 */

@Data
@TableName("bio_specificate_value")
public class BioSpecificateValue implements Serializable {
    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 规格id
     */
    @TableField
    private Integer specificateId;
    /**
     * 规格值
     */
    @TableField
    private String specificateValue;
}
