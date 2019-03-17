package cn.blz.controller;

import cn.blz.entity.*;
import cn.blz.service.BioProductSkuService;
import cn.blz.service.BioProductSpuService;
import cn.blz.service.BioSearchService;
import cn.blz.service.BioSpecificateService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * bio_product_sku 商品sku表(BioProductSku)表控制层
 *
 * @author blz
 * @since 2019-02-07 19:03:44
 */
@RestController
@RequestMapping("bioProductSku")
public class BioProductSkuController {
    /**
     * 服务对象
     */
    @Resource
    private BioProductSkuService bioProductSkuService;
    @Resource
    private BioSpecificateService bioSpecificateService;
    @Resource
    private BioProductSpuService bioProductSpuService;
    @Resource
    private BioSearchService bioSearchService;


    /**
     * 获取热门商品
     *
     * @return 热门商品集合
     */
    @GetMapping("getHotProduct")
    public String getHotProduct() {
        List<BioProductSku> list = this.bioProductSkuService.list(new QueryWrapper<BioProductSku>()
                .eq("enable", 1)
                .orderByDesc("sort")
                .last("limit 4"));

        return JSON.toJSONString(new Result(
                true,
                StatusCode.OK,
                "获取热门商品成功",
                getReListBySkuList(list)
        ));
    }

    /**
     * 根据商品id获取商品详情
     *
     * @param productId 商品id
     * @return 商品详情
     */
    @PostMapping("productDet/{productId}")
    public String productDet(@PathVariable("productId") Integer productId) {
        BioProductSku bioProductSku = this.bioProductSkuService.getById(productId);

        return JSON.toJSONString(new Result(true, StatusCode.OK, "获取商品详情成功", this.getSkuSepcificate(bioProductSku)));
    }

    /**
     * 根据id获取商品详情
     *
     * @param productId 商品id
     * @return 商品详情
     */
    @PostMapping("/getProductById/{productId}")
    public Map<String, String> productDet(@PathVariable("productId") String productId) {
        BioProductSku bioProductSku = this.bioProductSkuService.getById(productId);
        Map<String, String> map = new HashMap<>();

        map.put("name", bioProductSku.getName());
        map.put("img", bioProductSku.getImg());
        map.put("price", String.valueOf(bioProductSku.getPrice()));
        return map;
    }

    /**
     * 获取所有商品
     *
     * @return 商品
     */
    @GetMapping("getAllProduct")
    public String getAllProduct() {
        return JSON.toJSONString(new Result(true, StatusCode.OK, "获取所有商品成功", this.bioProductSkuService.list()));
    }


    //**************************************/


    /**
     * 根据品牌id获取商品
     *
     * @param brandId 品牌id
     * @return 对应商品
     */
    @PostMapping("getProductByBrand/{brandId}")
    public String getProductByBrand(@PathVariable("brandId") Integer brandId) {
        List<BioProductSpu> bioProductSpuList = this.bioProductSpuService.list(new QueryWrapper<BioProductSpu>().eq("brand_id", brandId));

        List<List<BioProductSku>> bioProductSkuList = new ArrayList<>();

        for (BioProductSpu bioProductSpu : bioProductSpuList) {
            bioProductSkuList.add(this.bioProductSkuService.list(new QueryWrapper<BioProductSku>().eq("spu_id", bioProductSpu.getId())));
        }

        List<BioProductSkuSpecificate> reList = null;

        for (List<BioProductSku> list : bioProductSkuList) {
            reList = getReListBySkuList(list);
        }

        return JSON.toJSONString(reList);
    }

    // todo:给前端声明这部分接口

    /**
     * 根据搜索关键词获取商品
     *
     * @param title 搜索关键词
     * @return 商品
     */
    @GetMapping("getByTitleOrContentLike/{title}")
    public String getByTitleOrContentLike(@PathVariable("title") String title) {
        List<Integer> idList = this.bioSearchService.getByTitleOrContentLike(title);

        List<BioProductSku> reList = new ArrayList<>();
        for (Integer i : idList) {
            BioProductSku bioProductSku = this.bioProductSkuService.getOne(new QueryWrapper<BioProductSku>().eq("id", i));
            reList.add(bioProductSku);
        }
        return JSON.toJSONString(new Result(true, StatusCode.OK, "搜索成功", reList));
    }

    // 自定义辅助函数*************************************

    private List<BioProductSkuSpecificate> getReListBySkuList(List<BioProductSku> list) {
        List<BioProductSkuSpecificate> reList = new ArrayList<>();

        for (BioProductSku bioProductSku : list) {
            BioProductSkuSpecificate bioProductSkuSpecificate = this.getSkuSepcificate(bioProductSku);
            reList.add(bioProductSkuSpecificate);
        }

        return reList;
    }

    private BioProductSkuSpecificate getSkuSepcificate(BioProductSku bioProductSku) {
        Map<String, List<String>> specificateByString = this.bioSpecificateService.getSpecificateByString(bioProductSku.getSpecificate());

        BioProductSpu bioProductSpu = this.bioProductSpuService.getById(bioProductSku.getSpuId());

        BioProductSkuSpecificate bioProductSkuSpecificate = new BioProductSkuSpecificate();
        bioProductSkuSpecificate.setIntro(bioProductSpu.getIntro());
        bioProductSkuSpecificate.setSpecificates(specificateByString);
        bioProductSkuSpecificate.setId(bioProductSku.getId());
        bioProductSkuSpecificate.setSpuId(bioProductSku.getSpuId());
        bioProductSkuSpecificate.setName(bioProductSku.getName());
        bioProductSkuSpecificate.setImg(bioProductSku.getImg());
        bioProductSkuSpecificate.setPrice(bioProductSku.getPrice());
        bioProductSkuSpecificate.setMarketPrice(bioProductSku.getMarketPrice());
        bioProductSkuSpecificate.setStock(bioProductSku.getStock());
        bioProductSkuSpecificate.setSpecificate(bioProductSku.getSpecificate());
        bioProductSkuSpecificate.setEnable(bioProductSku.getEnable());
        bioProductSkuSpecificate.setCreatedTime(bioProductSku.getCreatedTime());
        bioProductSkuSpecificate.setUpdateTime(bioProductSku.getUpdateTime());

        return bioProductSkuSpecificate;
    }
    // ***********************************************
}
