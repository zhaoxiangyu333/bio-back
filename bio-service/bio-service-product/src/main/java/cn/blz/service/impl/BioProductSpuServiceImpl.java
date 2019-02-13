package cn.blz.service.impl;

import cn.blz.dao.BioProductSpuDao;
import cn.blz.entity.BioProductSpu;
import cn.blz.service.BioProductSpuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * bio_product_spu 商品表(spu)(BioProductSpu)表服务实现类
 *
 * @author blz
 * @since 2019-02-08 13:12:54
 */
@Service("bioProductSpuService")
public class BioProductSpuServiceImpl extends ServiceImpl<BioProductSpuDao, BioProductSpu> implements BioProductSpuService {
    @Resource
    private BioProductSpuDao bioProductSpuDao;

}
