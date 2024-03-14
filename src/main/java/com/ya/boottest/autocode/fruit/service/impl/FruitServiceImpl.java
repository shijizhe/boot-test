package com.ya.boottest.autocode.fruit.service.impl;

import com.ya.boottest.autocode.fruit.entity.Fruit;
import com.ya.boottest.autocode.fruit.mapper.FruitMapper;
import com.ya.boottest.autocode.fruit.service.IFruitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 水果 服务实现类
 * </p>
 *
 * @author Ya Shi
 * @since 2023-08-12
 */
@Service
public class FruitServiceImpl extends ServiceImpl<FruitMapper, Fruit> implements IFruitService {

}
