package cn.blz.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * @author blz
 */
@FeignClient("order")
public interface BioOrderService {
    /**
     * 将缓存数据里面的商品添加到mysql
     *
     * @param userId            用户id
     * @param cartProductString 缓存商品字符串
     */
    @PostMapping("bioOrder/addRedisProductToMysql/{userId}/{cartProductString}")
    void addRedisProductToMysql(@PathVariable("userId") String userId, @PathVariable("cartProductString") String cartProductString);

    /**
     * 从数据库获取
     *
     * @param userId 用户id
     * @return 未支付的产品id
     */
    @PostMapping("bioOrder/getProductIdFromMysql/{userId}")
    Map<Integer, String> getProductIdFromMysql(@PathVariable("userId") Integer userId);

    /**
     * 根据用户id获取其订单
     * @param userIdByToken 用户id
     * @return 购物车json
     */
    @PostMapping("bioOrder/getOrderlistByUserId/{userId}")
    Map<String, Map<String,String>> getOrderlistByUserId(@PathVariable("userId") String userIdByToken);
}
