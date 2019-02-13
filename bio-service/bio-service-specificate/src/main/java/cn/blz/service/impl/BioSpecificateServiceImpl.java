package cn.blz.service.impl;

import cn.blz.entity.BioSpecificate;
import cn.blz.dao.BioSpecificateDao;
import cn.blz.service.BioSpecificateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * bio_specificate 商品规格表(BioSpecificate)表服务实现类
 *
 * @author blz
 * @since 2019-02-08 13:36:52
 */
@Service("bioSpecificateService")
public class BioSpecificateServiceImpl extends ServiceImpl<BioSpecificateDao, BioSpecificate> implements BioSpecificateService {
    @Resource
    private BioSpecificateDao bioSpecificateDao;

}