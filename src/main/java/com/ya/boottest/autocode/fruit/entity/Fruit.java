package com.ya.boottest.autocode.fruit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 水果
 * </p>
 *
 * @author Ya Shi
 * @since 2023-08-12
 */
@Getter
@Setter
@Schema(name = "Fruit", description = "$!{table.comment}")
@ToString
public class Fruit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "编码")
    private String frCode;

    @Schema(description = "名称")
    private String frName;

    @Schema(description = "价格")
    private BigDecimal frPrice;
}
