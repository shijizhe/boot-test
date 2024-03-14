package com.ya.boottest.manage.order.controller;

import com.ya.boottest.manage.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

/**
 * <p>
 *  订单相关接口
 * </p>
 *
 * @author Ya Shi
 * @since 2023/8/12 11:41
 */
@Slf4j
@RestController
@RequestMapping("/manage/order")
@Tag(name = "OrderController", description = "订单相关接口")
public class OrderController {

    @Resource
    OrderService orderService;

    @GetMapping("/export")
    @Operation(summary = "export", description = "订单导出")
    public Object export(@RequestParam(value = "orderNum") @NotBlank String orderNum){

      return orderService.export(orderNum);
        // return BaseResult.fail("导出失败：订单不存在");
    }

    @GetMapping("/import")
    @Operation(summary = "import", description = "订单导入")
    public Object importOrder(@RequestParam(value = "file") MultipartFile[] files){
        if(Objects.nonNull(files) && files.length > 0){

        }
        return null;
    }


}
