package com.ya.boottest.manage.fruit.service;

import com.ya.boottest.autocode.fruit.entity.Fruit;
import com.ya.boottest.autocode.fruit.service.IFruitService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *
 * </p>
 *
 * @author Ya Shi
 * @since 2024/2/29 19:27
 */
@Service
public class MyFruitServiceImpl implements MyFruitService {
    @Resource
    IFruitService iFruitService;

    @Override
    @Transactional
    public void TranTest() {
        Fruit fruit = new Fruit();
        fruit.setFrCode("test11111");
        iFruitService.save(fruit);
        throw new RuntimeException("yichang");
    }
}
