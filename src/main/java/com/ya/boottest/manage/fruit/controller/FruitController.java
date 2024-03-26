package com.ya.boottest.manage.fruit.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ya.boottest.autocode.fruit.entity.Fruit;
import com.ya.boottest.autocode.fruit.service.IFruitService;
import com.ya.boottest.config.aspect.TestApi;
import com.ya.boottest.manage.fruit.mapper.MyFruitMapper;
import com.ya.boottest.manage.fruit.service.MyFruitService;
import com.ya.boottest.utils.redis.RedisUtils;
import com.ya.boottest.utils.result.BaseResult;
import com.ya.boottest.utils.result.PageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 水果 前端控制器
 * </p>
 *
 * @author Ya Shi
 * @since 2023-05-05
 */
@Tag(name = "FruitController", description = "水果相关接口")
@Slf4j
@RestController
@RequestMapping("/anon/goods/fruit")
public class FruitController {

    @Autowired
    RedisUtils redisUtils;
    @Resource
    IFruitService fruitService;
    @Resource
    MyFruitMapper myFruitMapper;

    @Resource
    MyFruitService myFruitService;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @PutMapping("/update")
    @Operation(summary = "update", description = "更新水果信息")
    @ApiResponse(responseCode = "200",description = "返回信息描述示例",content = {
            @Content(
                    schema= @Schema(implementation = Fruit.class)
            )
    })
    public Object update(@RequestBody @Valid Fruit fruit) {
        return BaseResult.success(fruitService.updateById(fruit));
    }

    @PutMapping("/update2")
    @Operation(summary = "update2", description = "更新水果信息2")
    @Parameters({
            @Parameter(name = "code",description = "水果编码"),
            @Parameter(name = "name",description = "水果名称"),
            @Parameter(name = "price",description = "水果价格")
    })
    public Object update2( String code, String name, Integer price) {
        UpdateWrapper<Fruit> wrapper = new UpdateWrapper<>();
        wrapper.lambda()
                .eq(Fruit::getFrCode,code)
                .set(Fruit::getFrName,name)
                .set(Fruit::getFrPrice,price);
        return fruitService.update(wrapper);
    }

    @GetMapping("/list")
    @Operation(summary = "list", description = "获取水果信息")
    public Object list() {
       return BaseResult.success(fruitService.list());
    }

    @GetMapping("/pageQuery")
    @Operation(summary = "pageQuery", description = "分页查询-使用PageHelper")
    public Object pageQuery(@RequestParam(defaultValue = "0", value = "offset") int offset,
                            @RequestParam(defaultValue = "10", value = "limit") int limit,
                            @RequestParam(defaultValue = "", value = "sort") String sort,
                            @RequestParam(defaultValue = "", value = "order") String order) {
        PageHelper.offsetPage(offset, limit);
        if(StrUtil.isNotEmpty(sort)){
            PageHelper.orderBy(sort + " " + order);
        }
        List<Fruit> fruits = fruitService.list();
        PageInfo<Fruit> page = new PageInfo<>(fruits);
        PageResult<List<Fruit>> pageRes = new PageResult<>(page.getTotal(), fruits);
        return BaseResult.success(pageRes);
    }

    @GetMapping("/pageQuery2")
    @Operation(summary = "pageQuery2", description = "分页查询-使用MybatisPlus分页插件 IService")
    public Object pageQuery2(@RequestParam(defaultValue = "0", value = "offset") int offset,
                             @RequestParam(defaultValue = "10", value = "limit") int limit) {
        int pageNum = offset / limit + 1;
        Page<Fruit> page = new Page<>(pageNum, limit);
        page = fruitService.page(page);
        PageResult<List<Fruit>> pageRes = new PageResult<>(page.getTotal(), page.getRecords());
        return BaseResult.success(pageRes);
    }

    @GetMapping("/pageQuery3")
    @Operation(summary = "pageQuery3", description = "分页查询-使用MybatisPlus分页插件 baseMapper")
    public Object pageQuery3(@RequestParam(defaultValue = "0", value = "offset") int offset,
                             @RequestParam(defaultValue = "10", value = "limit") int limit) {
        int pageNum = offset / limit + 1;
        Page<Fruit> page = new Page<>(pageNum, limit);
        PageResult<List<Fruit>> pageRes = new PageResult<>(page.getTotal(), page.getRecords());
        return BaseResult.success(pageRes);
    }

    @GetMapping("/pageQuery4")
    @Operation(summary = "pageQuery4", description = "分页查询-使用MybatisPlus分页插件 IService")
    public Object pageQuery4(@RequestParam(defaultValue = "0", value = "offset") int offset,
                             @RequestParam(defaultValue = "10", value = "limit") int limit) {
        int pageNum = offset / limit + 1;
        Page<Fruit> page = new Page<>(pageNum, limit);
        // fruitService.page(page);
        fruitService.page(page, new QueryWrapper<>());
        PageResult<List<Fruit>> pageRes = new PageResult<>(page.getTotal(), page.getRecords());
        return BaseResult.success(pageRes);
    }

    @GetMapping("/pageQuery5")
    @Operation(summary = "pageQuery5", description = "分页查询-使用MybatisPlus分页插件 手写sql")
    public Object pageQuery5(@RequestParam(defaultValue = "0", value = "offset") int offset,
                             @RequestParam(defaultValue = "10", value = "limit") int limit,
                             @RequestParam(defaultValue = "", value = "sort") String sort,
                             @RequestParam(defaultValue = "", value = "order") String order) {
        int pageNum = offset / limit + 1;
        Page<Fruit> page = new Page<>(pageNum, limit);
        Map<String, Object> paramsMap = new HashMap<>();

        page.addOrder(new OrderItem(sort,"ASC".equals(order)));
        /* List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(sort,"ASC".equals(order)));
        page.addOrder(orderItems);*/
        // 放入自己的查询条件
        // fruitMapper.pageQueryFruit(page, paramsMap);
        PageResult<List<Fruit>> pageRes = new PageResult<>(page.getTotal(), page.getRecords());
        return BaseResult.success(pageRes);
    }

    @GetMapping("/getOne")
    @Operation(summary = "getOne", description = "Mp测试")
    public Object getOne(@RequestParam(defaultValue = "", value = "frCode") String frCode) {
        LambdaQueryWrapper<Fruit> wr = new LambdaQueryWrapper<>();
        wr.eq(Fruit::getFrCode,frCode);
        Fruit fruit = fruitService.getOne(wr);
        return BaseResult.success(fruit);
    }

    @GetMapping("/updateNull")
    @Operation(summary = "updateNull", description = "Mp update 测试")
    public Object updateNull() {
        Fruit fruit = new Fruit();
        fruit.setFrCode("kiwi");
        fruit.setFrName(null);
        LambdaQueryWrapper<Fruit> wr = new LambdaQueryWrapper<>();
        wr.eq(Fruit::getId, null);
        fruitService.update(fruit, wr);
        return BaseResult.success();
    }

    @GetMapping("/updateById")
    @Operation(summary = "updateById", description = "Mybatis Plus updateById 测试")
    public Object updateById() {
        LambdaQueryWrapper<Fruit> xx = new LambdaQueryWrapper<>();
        xx.eq(Fruit::getId, 999577L);
        Fruit fruit = new Fruit();
        fruit.setId(999577L);
        fruit.setFrPrice(BigDecimal.ONE);
        fruit.setFrCode("34");
        fruit.setFrName("name");

        boolean b = fruitService.update(fruit,xx);
        return BaseResult.success(b);
    }

    @GetMapping("/inTest")
    @Operation(summary = "inTest", description = "Mybatis Plus in 测试")
    public Object inTest() {
        LambdaQueryWrapper<Fruit> xx = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Fruit> xx2= new LambdaQueryWrapper<>();
         String[] arr = new String[]{"111","222"};
         String[] arr2 = null;
        xx.in(ArrayUtil.isNotEmpty(arr), Fruit::getFrCode, arr);
        xx2.in(ArrayUtil.isNotEmpty(arr2), Fruit::getFrCode, arr2);
        fruitService.list(xx);
        fruitService.list(xx2);
        return BaseResult.success();
    }

    @GetMapping("/falseTest")
    @Operation(summary = "falseTest", description = "Mybatis Plus false 测试")
    public Object falseTest() {
        // remove update  执行行数小于1 false

        // save 插入影响行数为0，返回false
        List<Fruit> xx = new ArrayList<>();
        boolean b = fruitService.saveBatch(xx);
        log.error("saveBatch:" + b);

        LambdaQueryWrapper<Fruit> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Fruit::getId, 9827L);
        List<Fruit> list = fruitService.list(wrapper);
        log.error(list.toString());
        return BaseResult.success();
    }

    @PostMapping("/testAspect")
    @Operation(summary = "testAspect", description = "testAspect")
    @TestApi
    public Object testAspect(@RequestBody Fruit fruit, @RequestParam Integer pageNum) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "jizhe");
        log.error("{}", map);
        return BaseResult.success();
    }

    @PostMapping("/handWriteMapper")
    @Operation(summary = "handWriteMapper", description = "手写的mapper查询数据")
    public Object handWriteMapper(@RequestParam(value = "fruitCode") String fruitCode) {
        return BaseResult.success(myFruitMapper.getOneFruit(fruitCode, "1"));
    }


    @PostMapping("/lamdaUpdate")
    @Operation(summary = "lamdaUpdate", description = "lamdaUpdate")
    public Object lambdaUpdate(@RequestParam(value = "fruitCode") String fruitCode) {
        fruitService.lambdaUpdate()
                .eq(Fruit::getFrCode, "test")
                .set(Fruit::getFrName, "变更水果")
                .update();
        return BaseResult.success();
    }

}
