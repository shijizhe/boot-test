package com.ya.boottest.autocode.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 水果订单行表
 * </p>
 *
 * @author Ya Shi
 * @since 2023-08-12
 */
@Getter
@Setter
@TableName("fruit_order_line")
@Schema(name = "FruitOrderLine", description = "$!{table.comment}")
public class FruitOrderLine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "订单编号")
    private String orderNum;

    @Schema(description = "行号")
    private Integer lineNum;

    @Schema(description = "行商品id")
    private String lineProdictId;

    @Schema(description = "行数量")
    private BigDecimal lineQuantity;

    @Schema(description = "行金额")
    private BigDecimal lineAmount;
}
