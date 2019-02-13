package cn.blz.service.impl;

import cn.blz.entity.BioAddress;
import cn.blz.dao.BioAddressDao;
import cn.blz.service.BioAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *  (BioAddress)表服务实现类
 *
 * @author blz
 * @since 2019-02-12 22:32:48
 */
@Service("bioAddressService")
public class BioAddressServiceImpl extends ServiceImpl<BioAddressDao, BioAddress> implements BioAddressService {
    @Resource
    private BioAddressDao bioAddressDao;

}