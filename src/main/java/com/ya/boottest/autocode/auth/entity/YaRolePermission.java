package com.ya.boottest.autocode.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 角色-权限关联表
 * </p>
 *
 * @author Ya Shi
 * @since 2024-03-22
 */
@Getter
@Setter
@TableName("ya_role_permission")
@Schema(name = "YaRolePermission", description = "$!{table.comment}")
public class YaRolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "角色Id")
    private String roleId;

    @Schema(description = "权限Id")
    private String permissionId;
}
