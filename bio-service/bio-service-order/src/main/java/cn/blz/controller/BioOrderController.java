package cn.blz.controller;

import cn.blz.entity.BioOrder;
import cn.blz.service.BioOrderService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * bio_orders 订单表(BioOrder)表控制层
 *
 * @author blz
 * @since 2019-02-11 16:58:13
 */
@RestController
@RequestMapping("bioOrder")
public class BioOrderController {
    /**
     * 服务对象
     */
    @Resource
    private BioOrderService bioOrderService;

    /**
     * 从数据库获取
     *
     * @param userId 用户id
     * @return 未支付的产品id
     */
    @PostMapping("getProductIdFromMysql/{userId}")
    public Map<Integer, String> getProductIdFromMysql(@PathVariable("userId") Integer userId) {
        Map<String, Integer> selectMap = new HashMap<>();
        selectMap.put("member_id", userId);
        selectMap.put("order_status", 0);
        List<BioOrder> list = this.bioOrderService.list(new QueryWrapper<BioOrder>().allEq(selectMap));

        Map<Integer, String> reMap = new HashMap<>();
        for (BioOrder bioOrder : list) {
            reMap.put(bioOrder.getId(), bioOrder.getProductId() + "_" + bioOrder.getProductCount());
        }
        return reMap;
    }

    /**
     * 将缓存数据里面的商品添加到mysql
     *
     * @param userId            用户id
     * @param cartProductString 缓存商品字符串
     */
    @PostMapping("addRedisProductToMysql/{userId}/{cartProductString}")
    public void addRedisProductToMysql(@PathVariable("userId") String userId, @PathVariable("cartProductString") String cartProductString) {
        if (!"null".equals(cartProductString)) {
            // todo: 根据商品字符串获取商品集合
            System.out.println("未完成将缓存数据里面的商品添加到mysql");
        }
//
//        BioOrder bioOrder = new BioOrder();
//        bioOrder.setId(null);
//        bioOrder.setOrderNo(UUID.randomUUID().toString());
//        bioOrder.setMemberId(Integer.parseInt(userId));
//        bioOrder.setOrderStatus(0);
//        bioOrder.setAfterStatus(0);
//        bioOrder.setProductCount(copy.getProductCount());
//        bioOrder.setProductAmountTotal(copy.getProductAmountTotal());
//        bioOrder.setOrderAmountTotal(copy.getOrderAmountTotal());
//        bioOrder.setLogisticsFee(copy.getLogisticsFee());
//        bioOrder.setOrderSettlementStatus(0);
//        bioOrder.setOrderSettlementTime(new Date());
//        bioOrder.setCreatedTime(new Date());
//        this.bioOrderService.save(bioOrder);
    }

    /**
     * 根据用户id获取其订单
     *
     * @param userIdByToken 用户id
     * @return 购物车json
     */
    @PostMapping("getOrderlistByUserId/{userId}")
    public Map<String, Map<String,String>> getOrderlistByUserId(@PathVariable("userId") String userIdByToken) {
        List<BioOrder> bioOrderList = this.bioOrderService.list(new QueryWrapper<BioOrder>().eq("member_id", userIdByToken));
        Map<String, Map<String,String>> map = new HashMap<>();
        for (BioOrder order : bioOrderList) {
            Map<String, String> orderMap = new HashMap<>();
            orderMap.put("createdTime",order.getCreatedTime().toString());
            orderMap.put("orderTotal",String.valueOf(order.getOrderAmountTotal()));
            orderMap.put("orderStatus",order.getOrderStatus().toString());
            orderMap.put("productId",order.getProductId().toString());
            orderMap.put("productNum",order.getProductCount().toString());
            map.put(String.valueOf(order.getId()),orderMap);
        }
        return map;
    }


}
