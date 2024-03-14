package com.ya.boottest.manage.fruit.mapper;

import com.ya.boottest.autocode.fruit.entity.Fruit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * fruit mapper
 * </p>
 *
 * @author Ya Shi
 * @since 2023/9/22 10:01
 */
@Mapper
public interface MyFruitMapper {
     List<Fruit> listFruit(Map<String, String> parsMap);

     List<Fruit> getOneFruit(String fruitCode,String id);


}
