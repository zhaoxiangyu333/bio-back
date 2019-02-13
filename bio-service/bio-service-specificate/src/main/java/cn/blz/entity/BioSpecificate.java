package cn.blz.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * bio_specificate 商品规格表(BioSpecificate)实体类
 *
 * @author blz
 * @since 2019-02-08 13:36:52
 */

@Data
@TableName("bio_specificate")
public class BioSpecificate implements Serializable {
    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 商品规格名称
     */
    @TableField
    private String name;
}
