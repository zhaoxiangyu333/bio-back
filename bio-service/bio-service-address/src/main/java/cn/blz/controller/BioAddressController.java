package cn.blz.controller;

import cn.blz.entity.BioAddress;
import cn.blz.entity.Result;
import cn.blz.entity.StatusCode;
import cn.blz.service.BioAddressService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (BioAddress)表控制层
 *
 * @author blz
 * @since 2019-02-12 22:32:48
 */
@RestController
@RequestMapping("bioAddress")
public class BioAddressController {
    /**
     * 服务对象
     */
    @Resource
    private BioAddressService bioAddressService;

    /**
     * 根据用户id获取用户所有订单地址json
     *
     * @param userId 用户id
     * @return 所有订单地址json
     */
    @PostMapping("getAddressByUserId/{userId}")
    public String getAddressByUserId(@PathVariable("userId") String userId) {
        return JSON.toJSONString(new Result(true, StatusCode.OK, "获取用户地址成功", this.bioAddressService.list(new QueryWrapper<BioAddress>().eq("user_id", userId))));
    }


    /**
     * 根据地址id获取地址
     * @param addressId 地址id
     * @return 地址信息
     */
    @PostMapping("addressById/{addressId}")
    public String addressById(@PathVariable("addressId") Integer addressId) {
        return JSON.toJSONString(new Result(true, StatusCode.OK,"获取对应id的地址成功", this.bioAddressService.getById(addressId)));
    }


}
