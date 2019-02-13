package cn.blz.service.impl;

import cn.blz.dao.BioProductSkuDao;
import cn.blz.entity.BioProductSku;
import cn.blz.service.BioProductSkuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * bio_product_sku 商品sku表(BioProductSku)表服务实现类
 *
 * @author blz
 * @since 2019-02-07 19:03:44
 */
@Service("bioProductSkuService")
public class BioProductSkuServiceImpl extends ServiceImpl<BioProductSkuDao, BioProductSku> implements BioProductSkuService {
    @Resource
    private BioProductSkuDao bioProductSkuDao;

}
