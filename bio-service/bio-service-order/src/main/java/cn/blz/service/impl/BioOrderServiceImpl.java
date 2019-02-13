package cn.blz.service.impl;

import cn.blz.entity.BioOrder;
import cn.blz.dao.BioOrderDao;
import cn.blz.service.BioOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * bio_orders 订单表(BioOrder)表服务实现类
 *
 * @author blz
 * @since 2019-02-11 16:58:13
 */
@Service("bioOrderService")
public class BioOrderServiceImpl extends ServiceImpl<BioOrderDao, BioOrder> implements BioOrderService {
    @Resource
    private BioOrderDao bioOrderDao;

}