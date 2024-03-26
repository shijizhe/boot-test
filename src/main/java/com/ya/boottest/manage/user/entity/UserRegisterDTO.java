package com.ya.boottest.manage.user.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 *  用户注册实体
 * </p>
 *
 * @author Ya Shi
 * @since 2024/3/15 16:20
 */
@Getter
@Setter
@ToString
@Tag(name = "UserRegisterDTO", description = "用户注册实体")
public class UserRegisterDTO {
    @NotNull(message = "用户id不能为空")
    @NotBlank(message = "用户id不能为空")
    @Schema(description = "用户id")
    private String userId;

    @NotNull(message = "用户名称")
    @NotBlank(message = "用户名称")
    @Schema(description = "用户名称")
    private String userName;

    @NotNull(message = "用户密码不能为空")
    @NotBlank(message = "用户密码不能为空")
    @Schema(description = "用户密码")
    private String userPassword;

}
