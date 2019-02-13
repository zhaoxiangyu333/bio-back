package cn.blz.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 *  (BioAddress)实体类
 *
 * @author blz
 * @since 2019-02-12 22:32:48
 */

@Data
@TableName("bio_address")
public class BioAddress implements Serializable {
    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 用户id
     */
    @TableField
    private Integer userId;
    /**
     * 收货人姓名
     */
    @TableField
    private String name;
    /**
     * 手机号
     */
    @TableField
    private String mobile;
    /**
     * 省
     */
    @TableField
    private String province;
    /**
     * 市
     */
    @TableField
    private String city;
    /**
     * 区
     */
    @TableField
    private String district;
    /**
     * 详细
     */
    @TableField
    private String remark;
    /**
     * 是否是默认地址 false
     */
    @TableField
    private Object isDefault;
    /**
     * 创建时间
     */
    @TableField
    private Date createdTime;
    /**
     * 修改时间
     */
    @TableField
    private Date updateTime;
}
