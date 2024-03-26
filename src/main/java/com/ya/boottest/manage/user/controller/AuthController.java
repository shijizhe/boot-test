package com.ya.boottest.manage.user.controller;

import com.ya.boottest.autocode.user.entity.YaUser;
import com.ya.boottest.manage.user.entity.UserLoginDTO;
import com.ya.boottest.manage.user.entity.UserRegisterDTO;
import com.ya.boottest.manage.user.entity.UserRegisterMapper;
import com.ya.boottest.manage.user.service.api.UserService;
import com.ya.boottest.utils.result.BaseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * <p>
 *  用户认证接口
 * </p>
 *
 * @author Ya Shi
 * @since 2024/3/13 9:39
 */
@Tag(name = "AuthController", description = "用户接口")
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    UserService userService;

    @PostMapping("/login")
    @Operation(summary = "login", description = "用户登录")
    public Object login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        return null;
    }

    @PostMapping("/register")
    @Operation(summary = "register", description = "用户注册")
    public Object register(@RequestBody @Valid UserRegisterDTO userRegisterDTO) {
        YaUser userById = userService.getUserById(userRegisterDTO.getUserId());
        if(Objects.nonNull(userById)){
            return BaseResult.fail("用户id已存在");
        }
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            YaUser yaUser = UserRegisterMapper.INSTANCE.registerToUser(userRegisterDTO);
            yaUser.setUserPassword(encoder.encode(userRegisterDTO.getUserPassword()));
            userService.insertUser(yaUser);
            return BaseResult.success("用户注册成功");
        }catch (Exception e){
            return BaseResult.fail("用户注册过程中遇到异常：" + e);
        }
    }
}
