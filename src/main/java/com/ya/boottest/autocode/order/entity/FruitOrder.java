package com.ya.boottest.autocode.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 水果订单表
 * </p>
 *
 * @author Ya Shi
 * @since 2023-08-12
 */
@Getter
@Setter
@TableName("fruit_order")
@Schema(name = "FruitOrder", description = "$!{table.comment}")
public class FruitOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "订单编号")
    private String orderNum;

    @Schema(description = "订单状态：已完成20")
    private String orderStatus;

    @Schema(description = "订单单位:01kg;02g;03市斤")
    private String orderUnitId;

    @Schema(description = "订单数量")
    private BigDecimal orderSumQuantity;

    @Schema(description = "订单金额")
    private BigDecimal orderSumAmount;

    @Schema(description = "销售人")
    private String orderSellerId;

    @Schema(description = "购买人")
    private String orderBuyerId;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
