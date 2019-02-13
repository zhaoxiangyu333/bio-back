package cn.blz.service.impl;

import cn.blz.entity.BioBrand;
import cn.blz.dao.BioBrandDao;
import cn.blz.service.BioBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * bio_brand 商品品牌(BioBrand)表服务实现类
 *
 * @author blz
 * @since 2019-02-08 23:51:18
 */
@Service("bioBrandService")
public class BioBrandServiceImpl extends ServiceImpl<BioBrandDao, BioBrand> implements BioBrandService {
    @Resource
    private BioBrandDao bioBrandDao;

}