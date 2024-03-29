package com.ya.boottest.manage.user.service.impl;

import com.ya.boottest.autocode.auth.entity.YaUserRole;
import com.ya.boottest.autocode.auth.service.IYaUserRoleService;
import com.ya.boottest.autocode.user.entity.YaUser;
import com.ya.boottest.autocode.user.service.IYaUserService;
import com.ya.boottest.manage.user.mapper.UserMapper;
import com.ya.boottest.manage.user.service.api.UserService;
import com.ya.boottest.utils.crypto.AESUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ya.boottest.utils.crypto.AESUtils.SALT_LENGTH;

/**
 * <p>
 * 用户服务
 * </p>
 *
 * @author Ya Shi
 * @since 2024/3/15 16:07
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    IYaUserService iYaUserService;

    @Resource
    IYaUserRoleService iYaUserRoleService;

    @Resource
    UserMapper userMapper;

    @Override
    public YaUser getUserById(String userId) {
        return iYaUserService.lambdaQuery().eq(YaUser::getUserId, userId).one();
    }

    @Override
    public void insertUser(YaUser user) {
        iYaUserService.save(user);
    }

    @Override
    public String encryptPassword(String password) {
        // 密码加密
        String tmp = AESUtils.encryptBase64(password);
        return AESUtils.encryptBase64(AESUtils.generateSalt() + tmp);
    }

    @Override
    public String decryptPassword(String encryptedPassword) {
        String tmp = AESUtils.decryptBase64(encryptedPassword);
        String tmp2 = tmp.substring(SALT_LENGTH);
        return AESUtils.decryptBase64(tmp2);
    }

    @Override
    public List<YaUserRole> listRoleById(String userId) {
        return iYaUserRoleService.lambdaQuery().eq(YaUserRole::getUserId, userId).list();
    }

    @Override
    public List<String> listAuthorityById(String userId) {
        return userMapper.listAuthorityById(userId);
    }
}
