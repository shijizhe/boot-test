package com.ya.boottest.autocode.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Ya Shi
 * @since 2023-08-12
 */
@Getter
@Setter
@TableName("ya_user")
@Schema(name = "YaUser", description = "$!{table.comment}")
public class YaUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "账号")
    private String userId;

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "密码")
    private String userPassword;
}
