package cn.blz.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 *  (BioUser)实体类
 *
 * @author blz
 * @since 2019-02-11 22:15:28
 */

@Data
@TableName("bio_user")
public class BioUser implements Serializable {
    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 用户名
     */
    @TableField
    private String username;
    /**
     * 密码
     */
    @TableField
    private String passwd;
    /**
     * 用户昵称
     */
    @TableField
    private String name;
    /**
     * 手机号
     */
    @TableField
    private String phonenumber;
    /**
     * 邮箱
     */
    @TableField
    private String email;
    @TableField
    private String img;
    /**
     * 创建时间
     */
    @TableField
    private Date createdTime;
    /**
     * 修改时间
     */
    @TableField
    private Date updateTiem;
}
