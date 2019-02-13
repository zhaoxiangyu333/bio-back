package cn.blz.controller;

import cn.blz.entity.BioSpecificateValue;
import cn.blz.service.BioSpecificateValueService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * bio_specificate_value 商品属性值表(BioSpecificateValue)表控制层
 *
 * @author blz
 * @since 2019-02-08 13:37:05
 */
@RestController
@RequestMapping("bioSpecificateValue")
public class BioSpecificateValueController {
    /**
     * 服务对象
     */
    @Resource
    private BioSpecificateValueService bioSpecificateValueService;

}