package cn.blz.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("product")
public interface BioProductService {
    /**
     * 根据品牌id获取商品
     * @param brandId 品牌id
     * @return 对应商品
     */
    @PostMapping("bioProductSku/getProductByBrand/{brandId}")
    String getProductByBrand(@PathVariable("brandId") Integer brandId);
}
