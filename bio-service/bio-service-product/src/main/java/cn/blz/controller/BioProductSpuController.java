package cn.blz.controller;

import cn.blz.service.BioProductSpuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * bio_product_spu 商品表(spu)(BioProductSpu)表控制层
 *
 * @author blz
 * @since 2019-02-08 13:12:54
 */
@RestController
@RequestMapping("bioProductSpu")
public class BioProductSpuController {
    /**
     * 服务对象
     */
    @Resource
    private BioProductSpuService bioProductSpuService;
}
