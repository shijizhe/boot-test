package com.ya.boottest.autocode.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户-角色关联表
 * </p>
 *
 * @author Ya Shi
 * @since 2024-03-22
 */
@Getter
@Setter
@TableName("ya_user_role")
@Schema(name = "YaUserRole", description = "$!{table.comment}")
public class YaUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "角色Id")
    private String roleId;

    @Schema(description = "账号Id")
    private String userId;
}
