package cn.blz.entity;

import lombok.Data;

/**
 * @author blz
 */
@Data
public class BioOrderProduct {
    private String orderId;
    private String createdTime;
    private String orderStatus;
    private String orderTotal;
    private String productNum;
    private String productName;
    private String productPrice;
    private String productImg;
}
