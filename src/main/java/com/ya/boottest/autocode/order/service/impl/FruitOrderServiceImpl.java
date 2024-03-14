package com.ya.boottest.autocode.order.service.impl;

import com.ya.boottest.autocode.order.entity.FruitOrder;
import com.ya.boottest.autocode.order.mapper.FruitOrderMapper;
import com.ya.boottest.autocode.order.service.IFruitOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 水果订单表 服务实现类
 * </p>
 *
 * @author Ya Shi
 * @since 2023-08-12
 */
@Service
public class FruitOrderServiceImpl extends ServiceImpl<FruitOrderMapper, FruitOrder> implements IFruitOrderService {

}
