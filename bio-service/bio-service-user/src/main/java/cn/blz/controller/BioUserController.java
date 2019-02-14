package cn.blz.controller;

import cn.blz.entity.*;
import cn.blz.service.BioAddressService;
import cn.blz.service.BioOrderService;
import cn.blz.service.BioProductService;
import cn.blz.service.BioUserService;
import cn.blz.utils.BioUtil;
import cn.blz.utils.JwtUtil;
import cn.blz.utils.RedisUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * (BioUser)表控制层
 *
 * @author blz
 * @since 2019-02-09 14:34:10
 */
@RestController
@RequestMapping("bioUser")
public class BioUserController {
    /**
     * 服务对象
     */
    @Resource
    private BioUserService bioUserService;
    @Resource
    private BioOrderService bioOrderService;
    @Resource
    private BioProductService bioProductService;
    @Resource
    private BioAddressService bioAddressService;

    private final RedisUtil redisUtil;
    private final BCryptPasswordEncoder encoder;
    private final BioUtil bioUtil;
    private final JwtUtil jwtUtil;

    private final static String NULL = "null";
    private final static String LEFTCONTAIN = "[";
    private final static String RIGHTCONTAIN = "]";
    private final static String REDISUSER = "user-";
    private final static String REDISCART = "cart-";

    @Autowired
    public BioUserController(RedisUtil redisUtil, BCryptPasswordEncoder encoder, BioUtil bioUtil, JwtUtil jwtUtil) {
        this.redisUtil = redisUtil;
        this.encoder = encoder;
        this.bioUtil = bioUtil;
        this.jwtUtil = jwtUtil;
    }


    /**
     * 根据用户id返回用户token
     *
     * @param token 用户id
     * @return 用户token
     */
    @PostMapping("userInfo/{token}")
    public String getUserInfo(@PathVariable("token") String token) {
        if (NULL.equals(token)) {
            return JSON.toJSONString(new Result(false, StatusCode.REMOTEERROR, "用户token为null", null));
        } else {
            String userId = getUserIdByToken(token);
            if (NULL.equals(userId)) {
                return JSON.toJSONString(
                        new Result(
                                false,
                                StatusCode.REMOTEERROR,
                                "用户token过期",
                                null
                        )
                );
            }
            String redisToken = this.redisUtil.get(0, REDISUSER + userId);
            if (redisToken == null) {
                return JSON.toJSONString(
                        new Result(
                                false,
                                StatusCode.REMOTEERROR,
                                "用户未登录",
                                null
                        )
                );
            } else {
                BioUser user = this.bioUserService.getById(this.getUserIdByToken(token));

                BioUserToken bioUserToken = new BioUserToken();
                bioUserToken.setToken(token);
                bioUserToken.setId(user.getId());
                bioUserToken.setUsername(user.getUsername());
                bioUserToken.setPasswd(user.getPasswd());
                bioUserToken.setName(user.getName());
                bioUserToken.setPhonenumber(user.getPhonenumber());
                bioUserToken.setEmail(user.getEmail());
                bioUserToken.setImg(user.getImg());
                bioUserToken.setCreatedTime(user.getCreatedTime());
                bioUserToken.setUpdateTiem(user.getUpdateTiem());
                return bioUtil.dispelBackslash(JSON.toJSONString(new Result(true, StatusCode.OK, "用户是否定登录验证成功，返回token", bioUserToken)));
            }
        }
    }


    /**
     * 用户登录
     *
     * @param userName 用户名
     * @param passWd   密码
     * @return 签发的用户token
     */
    @PostMapping("login/{userName}/{passWd}")
    public String login(@PathVariable("userName") String userName, @PathVariable("passWd") String passWd) {
        BioUser user = this.bioUserService.getOne(new QueryWrapper<BioUser>().eq("username", userName));
        if (user != null && encoder.matches(passWd, user.getPasswd())) {
            String token = jwtUtil.createJWT(String.valueOf(user.getId()), user.getName(), "user");

            // 保存到redis中
            this.redisUtil.set(0, REDISUSER + user.getId(), token);
            // 将mysql订单数据加入到购物车
            this.addProductToRedis(this.bioOrderService.getProductIdFromMysql(user.getId()), user.getId());

            return bioUtil.dispelBackslash(JSON.toJSONString(new Result(true, StatusCode.OK, "用户登录成功", token)));
        } else {
            return JSON.toJSONString(new Result(false, StatusCode.LOGINERROR, "用户登录失败", null));
        }
    }

    /**
     * 用户退出，清除redis其token信息
     */
    @PostMapping("loginOut/{token}")
    public void loginOut(@PathVariable("token") String token) {
        String userId = getUserIdByToken(token);
        this.redisUtil.del(0, REDISUSER + userId);
//        todo:将缓存数据库信息添加到mysql
//        this.bioOrderService.addRedisProductToMysql(userId,
//                this.redisUtil.get(1, "cart-" + userId) == null ? "null" : this.redisUtil.get(1, "cart-" + userId));
    }

    /**
     * 用户登录后添加商品到redis
     *
     * @param token      用户token
     * @param productMsg 购物车简单商品详情
     */
    @PostMapping("addCartBatch/{token}/{productMsg}")
    public void localCartToRedis(@PathVariable("token") String token, @PathVariable("productMsg") String productMsg) {
        String userId = this.getUserIdByToken(token);
        productMsg = LEFTCONTAIN + productMsg + RIGHTCONTAIN;
        List<BioCartFront> bioCartFrontList = JSONObject.parseArray(JSONObject.toJSONString(JSON.parseArray(productMsg)), BioCartFront.class);
        for (BioCartFront bioCartFront : bioCartFrontList) {
            this.addCart(bioCartFront.getProductId(), bioCartFront.getProductNum(), userId);
        }
    }

    /**
     * 获取用户购物车列表
     *
     * @param token 用户token
     * @return 购物车列表
     */
    @PostMapping("cartList/{token}")
    public String cartList(@PathVariable("token") String token) {
        String s = this.redisUtil.get(1, REDISCART + this.getUserIdByToken(token));
        if (s != null) {
            if (!s.contains(LEFTCONTAIN)) {
                s = LEFTCONTAIN + s + RIGHTCONTAIN;
            }
            List<BioCart> bioCartList = JSONObject.parseArray(JSONObject.toJSONString(JSON.parseArray(s)), BioCart.class);

            return JSON.toJSONString(
                    new Result(true,
                            StatusCode.OK,
                            "获取用户购物城成功",
                            bioCartList
                    ));
        }
        return null;
    }


    /**
     * 加入商品到缓存数据库中
     *
     * @param productId 商品id
     * @param token     用户token
     */
    @PostMapping("addCart/{productId}/{token}")
    public void addCart(@PathVariable("productId") String productId, @PathVariable("token") String token) {
        String userId = this.getUserIdByToken(token);

        this.mapToBioCartToRedis(userId, this.bioProductService.getProductById(productId), productId, null);
    }


    /**
     * 加入商品到缓存数据库中（登录后）
     *
     * @param productId  商品id
     * @param productNum 商品个数
     * @param userId     用户id
     */
    @PostMapping("addCartAfterLogin/{productId}/{productNum}/{userId}")
    public void addCart(@PathVariable("productId") String productId, @PathVariable("productNum") Integer productNum, @PathVariable("userId") String userId) {
        this.mapToBioCartToRedis(userId, this.bioProductService.getProductById(productId), productId, productNum);
    }

    /**
     * 从缓存中删除商品
     *
     * @param token     用户token
     * @param productId 被删除的商品的id
     */
    @PostMapping("delCart/{token}/{productId}")
    public void delCart(@PathVariable("token") String token, @PathVariable("productId") Integer productId) {
        String userId = this.getUserIdByToken(token);
        String redisCartToken = this.redisUtil.get(1, REDISCART + userId);
        List<BioCart> bioCartList = JSONObject.parseArray(JSONObject.toJSONString(JSON.parseArray(redisCartToken)), BioCart.class);

        for (BioCart bioCart : bioCartList) {
            if (bioCart.getProductId().intValue() == productId.intValue()) {
                bioCartList.remove(bioCart);
                break;
            }
        }

        // 更新redis数据
        this.redisUtil.set(1, REDISCART + userId, JSON.toJSONString(bioCartList));
    }

    /**
     * 修改商品从缓存中
     *
     * @param token      用户token
     * @param productId  商品id
     * @param productNum 商品选购个数
     * @param checked    是否点击
     * @return 新购物车数组
     */
    @PostMapping("cartEdit/{token}/{productId}/{productNum}/{checked}")
    public String cartEdit(@PathVariable("token") String token, @PathVariable("productId") Integer productId, @PathVariable("productNum") Integer productNum, @PathVariable("checked") String checked) {
        String userId = getUserIdByToken(token);

        String redisCartToken = this.redisUtil.get(1, REDISCART + userId);

        List<BioCart> bioCartList = JSONObject.parseArray(JSONObject.toJSONString(JSON.parseArray(redisCartToken)), BioCart.class);

        for (BioCart bioCart : bioCartList) {
            if (productId.intValue() == bioCart.getProductId().intValue()) {
                bioCart.setProductNum(productNum);
                bioCart.setChecked(checked);
                break;
            }
        }
        // 更新redis数据
        this.redisUtil.set(1, REDISCART + userId, JSON.toJSONString(bioCartList));

        return JSON.toJSONString(
                new Result(
                        true,
                        StatusCode.OK,
                        "更新数据库成功",
                        bioCartList
                )
        );
    }

    /**
     * 全选商品
     *
     * @param token 用户token
     */
    @PostMapping("editCheckAll/{token}")
    public void editCheckAll(@PathVariable("token") String token) {
        String userId = this.getUserIdByToken(token);
        String redisCartToken = this.redisUtil.get(1, "cart-" + userId);
        List<BioCart> bioCartList = JSONObject.parseArray(JSONObject.toJSONString(JSON.parseArray(redisCartToken)), BioCart.class);
        for (BioCart bioCart : bioCartList) {
            bioCart.setChecked("1");
        }

        // 更新redis数据
        this.redisUtil.set(1, "cart-" + userId, JSON.toJSONString(bioCartList));
    }

    /**
     * 获取用户订单地址
     *
     * @param token 用户token
     * @return 地址
     */
    @PostMapping("addressList/{token}")
    public String addressList(@PathVariable("token") String token) {
        return this.bioAddressService.getAddressByUserId(this.getUserIdByToken(token));
    }


    /**
     * 获取用户所有订单
     *
     * @param token 用户token
     * @return 订单表
     */
    @PostMapping("orderList/{token}")
    public String orderList(@PathVariable("token") String token) {
        List<BioOrderProduct> relist = new ArrayList<>();
        Map<String, Map<String, String>> orderMap = this.bioOrderService.getOrderlistByUserId(this.getUserIdByToken(token));
        for (Map.Entry<String, Map<String, String>> stringMapEntry : orderMap.entrySet()) {
            BioOrderProduct bioOrderProduct = new BioOrderProduct();
            bioOrderProduct.setOrderId(stringMapEntry.getKey());
            bioOrderProduct.setCreatedTime(stringMapEntry.getValue().get("createdTime"));
            bioOrderProduct.setOrderStatus(stringMapEntry.getValue().get("orderStatus"));
            bioOrderProduct.setOrderTotal(stringMapEntry.getValue().get("orderTotal"));

            Map<String, String> productMap = this.bioProductService.getProductById(stringMapEntry.getValue().get("productId"));

            bioOrderProduct.setProductNum(stringMapEntry.getValue().get("productNum"));
            bioOrderProduct.setProductName(productMap.get("name"));
            bioOrderProduct.setProductPrice(productMap.get("price"));
            bioOrderProduct.setProductImg(productMap.get("img"));

            relist.add(bioOrderProduct);

        }
        return JSON.toJSONString(new Result(true, StatusCode.OK, "获取用户订单成功", relist));
    }

    /**
     * todo:删除订单
     */
    public void delOrder() {

    }


    /**
     * 生成订单
     *
     * @param token     用户token
     * @param addressId 地址id
     * @return 订单编号
     */
    @PostMapping("generalOrder/{token}/{addressId}")
    public String generalOrder(@PathVariable("token") String token, @PathVariable("addressId") Integer addressId) {
        // 随机成成订单号
        String orderNo = UUID.randomUUID().toString();
        // 用户id
        String userId = this.getUserIdByToken(token);


        List<Map<String, String>> list = new ArrayList<>();
        List<BioCart> bioCartList = JSONObject.parseArray(JSONObject.toJSONString(JSON.parseArray(this.redisUtil.get(1, REDISCART + userId))), BioCart.class);
        for (int i = bioCartList.size() - 1; i >= 0; i--) {
            if ("1".equals(bioCartList.get(i).getChecked())) {
                Map<String, String> map = new HashMap<>();
                map.put("productId", String.valueOf(bioCartList.get(i).getProductId()));
                map.put("productNum", String.valueOf(bioCartList.get(i).getProductNum()));
                map.put("productPrice", String.valueOf(bioCartList.get(i).getProductPrice()));
                list.add(map);
//                bioCartList.remove(bioCartList.get(i));
            }
        }

        // 更新缓存数据库
//        this.redisUtil.set(1, REDISCART + userId, JSON.toJSONString(bioCartList));

        this.bioOrderService.generalOrder(orderNo, userId, addressId, JSON.toJSONString(list));

        return JSON.toJSONString(new Result(true, StatusCode.OK, "添加未支付订单成功", orderNo));
    }

    // todo 支付接口
    @PostMapping("payMent/{token}/{orderNo}")
    public String payMent(@PathVariable("orderNo") String orderNo, @PathVariable("token") String token) {
        List<Integer> integers = this.bioOrderService.updateOrderStatus(orderNo);

        String userId = this.getUserIdByToken(token);
        List<BioCart> bioCartList = JSONObject.parseArray(JSONObject.toJSONString(
                JSON.parseArray(this.redisUtil.get(1, REDISCART + userId))), BioCart.class);
        System.out.println(integers.size());
        for (Integer ig : integers) {
            for (int i = bioCartList.size() - 1; i >= 0; i--) {
                if (ig.equals(bioCartList.get(i).getProductId())) {
                    bioCartList.remove(bioCartList.get(i));
                }
            }
        }

        // 更新缓存数据库
        this.redisUtil.set(1, REDISCART + userId, JSON.toJSONString(bioCartList));


        return JSON.toJSONString(new Result(true, StatusCode.OK, "支付接口功能暂时未做", null));
    }


    /**
     * 将数据库未支付的订单加入到redis中
     *
     * @param map    mysql数据
     * @param userId 用户id
     */
    private void addProductToRedis(Map<Integer, String> map, Integer userId) {
        for (String productIdAndCount : map.values()) {
            String[] lines = productIdAndCount.split("_");
            String productId = lines[0];
            String productCount = lines[1];
            this.addCart(productId, Integer.parseInt(productCount), String.valueOf(userId));
        }
    }

    /**
     * 根据token获取用户id
     *
     * @param token 用户token
     * @return 用户id
     */
    private String getUserIdByToken(String token) {
        Claims claims;
        try {
            claims = jwtUtil.parseJWT(token);
        } catch (ExpiredJwtException | MalformedJwtException ignored) {
            return NULL;
        }

        return claims.getId();
    }


    /**
     * 合并重复商品
     *
     * @param key 用户id
     * @param s   用户缓存购物车json
     */
    private void uniteCart(String key, String s, BioCart bioCart) {
        if (!s.contains(LEFTCONTAIN)) {
            s = LEFTCONTAIN + s + RIGHTCONTAIN;
        }

        List<BioCart> bioCartList = JSONObject.parseArray(JSONObject.toJSONString(JSON.parseArray(s)), BioCart.class);

        boolean ifExist = false;

        for (BioCart cart : bioCartList) {
            if (cart.getProductId().intValue() == bioCart.getProductId().intValue()) {
                ifExist = true;
                cart.setProductNum(cart.getProductNum() + 1);
                break;
            }
        }

        if (!ifExist) {
            bioCartList.add(bioCart);
        }
        // 更新redis数据
        this.redisUtil.set(1, key, JSON.toJSONString(bioCartList));
    }


    /**
     * 将map数据转换成购物车entiy并更新到redis中
     *
     * @param userId     用户id
     * @param map        商品数据
     * @param productId  商品id
     * @param productNum 商品个数
     */
    private void mapToBioCartToRedis(String userId, Map<String, String> map, String productId, Integer productNum) {
        BioCart bioCart = new BioCart();
        bioCart.setProductId(Integer.parseInt(productId));
        bioCart.setProductName(map.get("name"));
        bioCart.setProductImg(map.get("img"));
        bioCart.setProductPrice(Double.parseDouble(map.get("price")));
        if (productNum == null) {
            bioCart.setProductNum(1);
        } else {
            bioCart.setProductNum(productNum);
        }

        bioCart.setChecked("1");

        if (this.redisUtil.exists(1, REDISCART + userId)) {
            uniteCart(REDISCART + userId, this.redisUtil.get(1, REDISCART + userId), bioCart);
        } else {
            this.redisUtil.set(1, REDISCART + userId, JSON.toJSONString(bioCart));
        }


    }


}
