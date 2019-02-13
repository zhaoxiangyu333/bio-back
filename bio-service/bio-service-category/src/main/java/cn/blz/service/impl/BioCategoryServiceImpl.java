package cn.blz.service.impl;

import cn.blz.entity.BioCategory;
import cn.blz.dao.BioCategoryDao;
import cn.blz.service.BioCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * bio_category 商品分类表(BioCategory)表服务实现类
 *
 * @author blz
 * @since 2019-02-07 14:53:16
 */
@Service("bioCategoryService")
public class BioCategoryServiceImpl extends ServiceImpl<BioCategoryDao, BioCategory> implements BioCategoryService {
    @Resource
    private BioCategoryDao bioCategoryDao;

}