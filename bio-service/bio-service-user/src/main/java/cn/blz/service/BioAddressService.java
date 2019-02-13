package cn.blz.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author blz
 */
@FeignClient("address")
public interface BioAddressService {
    /**
     * 根据用户id获取用户所有订单地址json
     *
     * @param userId 用户id
     * @return 所有订单地址json
     */
    @PostMapping("bioAddress/getAddressByUserId/{userId}")
    String getAddressByUserId(@PathVariable("userId") String userId);
}
