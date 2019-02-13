package cn.blz.controller;

import cn.blz.entity.BioBrand;
import cn.blz.entity.BioBrandProducts;
import cn.blz.entity.Result;
import cn.blz.entity.StatusCode;
import cn.blz.service.BioBrandService;
import cn.blz.service.BioProductService;
import cn.blz.utils.BioUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * bio_brand 商品品牌(BioBrand)表控制层
 *
 * @author blz
 * @since 2019-02-08 23:51:18
 */
@RestController
@RequestMapping("bioBrand")
public class BioBrandController {
    /**
     * 服务对象
     */
    @Resource
    private BioBrandService bioBrandService;
    @Resource
    private BioProductService bioProductService;

    private final BioUtil bioUtil;

    @Autowired
    public BioBrandController(BioUtil bioUtil) {
        this.bioUtil = bioUtil;
    }

    /**
     * 获取热门品牌和商品
     *
     * @return 热门品牌和商品
     */
    @GetMapping("getHotBrand")
    private String getHotBrand() {
        List<BioBrand> list = this.bioBrandService.list(new QueryWrapper<BioBrand>()
                .orderByDesc("sort")
                .last("limit 4"));


        List<BioBrandProducts> reList = new ArrayList<>();

        for (BioBrand bioBrand : list) {
            BioBrandProducts bioBrandProducts = new BioBrandProducts();
            bioBrandProducts.setProducts(this.bioProductService.getProductByBrand(bioBrand.getId()));
            bioBrandProducts.setId(bioBrand.getId());
            bioBrandProducts.setName(bioBrand.getName());
            bioBrandProducts.setImg(bioBrand.getImg());
            bioBrandProducts.setSort(bioBrand.getSort());

            reList.add(bioBrandProducts);

        }

        return bioUtil.dispelBackslash(JSON.toJSONString(new Result(true, StatusCode.OK, "获取热门品牌成功",reList)));

    }
}
