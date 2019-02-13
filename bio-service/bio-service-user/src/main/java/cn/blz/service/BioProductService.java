package cn.blz.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * @author blz
 */
@FeignClient("product")
public interface BioProductService {
    /**
     * 根据id获取商品详情
     * @param productId 商品id
     * @return 商品详情
     */
    @PostMapping("bioProductSku/getProductById/{productId}")
    Map<String, String> getProductById(@PathVariable("productId") String productId);
}
