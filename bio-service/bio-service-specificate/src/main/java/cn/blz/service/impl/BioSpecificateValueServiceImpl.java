package cn.blz.service.impl;

import cn.blz.entity.BioSpecificateValue;
import cn.blz.dao.BioSpecificateValueDao;
import cn.blz.service.BioSpecificateValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * bio_specificate_value 商品属性值表(BioSpecificateValue)表服务实现类
 *
 * @author blz
 * @since 2019-02-08 13:37:05
 */
@Service("bioSpecificateValueService")
public class BioSpecificateValueServiceImpl extends ServiceImpl<BioSpecificateValueDao, BioSpecificateValue> implements BioSpecificateValueService {
    @Resource
    private BioSpecificateValueDao bioSpecificateValueDao;

}