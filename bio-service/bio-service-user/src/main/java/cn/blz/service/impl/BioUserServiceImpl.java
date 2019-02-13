package cn.blz.service.impl;

import cn.blz.entity.BioUser;
import cn.blz.dao.BioUserDao;
import cn.blz.service.BioUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *  (BioUser)表服务实现类
 *
 * @author blz
 * @since 2019-02-09 14:34:10
 */
@Service("bioUserService")
public class BioUserServiceImpl extends ServiceImpl<BioUserDao, BioUser> implements BioUserService {
    @Resource
    private BioUserDao bioUserDao;

}