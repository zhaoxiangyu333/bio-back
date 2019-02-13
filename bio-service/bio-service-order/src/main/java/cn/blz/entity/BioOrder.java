package cn.blz.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * bio_order 订单表(BioOrder)实体类
 *
 * @author blz
 * @since 2019-02-11 18:23:55
 */

@Data
@TableName("bio_order")
public class BioOrder implements Serializable {
    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 订单id
     */
    @TableField
    private String orderNo;
    /**
     * 客户id
     */
    @TableField
    private Integer memberId;
    /**
     * 商品id
     */
    @TableField
    private Integer productId;
    /**
     * 订单状态 0未付款,1已付款,2已发货,3已签收,-1退货申请,-2退货中,-3已退货,-4取消交易 -5撤销申请
     */
    @TableField
    private Integer orderStatus;
    /**
     * 用户售后状态 0 未发起售后 1 申请售后 -1 售后已取消 2 处理中 200 处理完毕
     */
    @TableField
    private Integer afterStatus;
    /**
     * 商品数量
     */
    @TableField
    private Integer productCount;
    /**
     * 商品总价
     */
    @TableField
    private Double productAmountTotal;
    /**
     * 实际付款金额
     */
    @TableField
    private Double orderAmountTotal;
    /**
     * 运费金额
     */
    @TableField
    private Double logisticsFee;
    /**
     * 收货地址编码
     */
    @TableField
    private Integer addressId;
    /**
     * 支付渠道 0余额 1微信 2支付宝
     */
    @TableField
    private Integer payChannel;
    /**
     * 订单支付单号
     */
    @TableField
    private String outTradeNo;
    /**
     * 第三方支付流水号
     */
    @TableField
    private String escrowTradeNo;
    /**
     * 付款时间
     */
    @TableField
    private Date payTime;
    /**
     * 发货时间
     */
    @TableField
    private Date deliveryTime;
    /**
     * 订单结算状态 0未结算 1已结算
     */
    @TableField
    private Integer orderSettlementStatus;
    /**
     * 订单结算时间
     */
    @TableField
    private Date orderSettlementTime;
    /**
     * 创建时间
     */
    @TableField
    private Date createdTime;
    /**
     * 更新时间
     */
    @TableField
    private Date updatedTime;
}
