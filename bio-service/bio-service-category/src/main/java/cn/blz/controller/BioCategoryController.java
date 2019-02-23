package cn.blz.controller;

import cn.blz.service.BioCategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import io.swagger.annotations.*;


/**
 * bio_category 商品分类表(BioCategory)表控制层
 *
 * @author blz
 * @since 2019-02-07 14:53:16
 */
@Api(value = "商品类别", description = "商品类别")
@RestController
@RequestMapping("bioCategory")
public class BioCategoryController {
    /**
     * 服务对象
     */
    @Resource
    private BioCategoryService bioCategoryService;

}
