package cn.blz.entity;

import lombok.Data;

/**
 * @author blz
 */
@Data
public class BioCart {
    private Integer productId;
    private String productName;
    private String productImg;
    private double productPrice;
    private int productNum;
    private String checked;
}
