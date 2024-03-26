package com.ya.boottest.manage.user.service.api;

import com.ya.boottest.autocode.auth.entity.YaUserRole;
import com.ya.boottest.autocode.user.entity.YaUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  用户服务
 * </p>
 *
 * @author Ya Shi
 * @since 2024/3/15 16:06
 */
@Service
public interface UserService {

    YaUser getUserById(String userId);

    void insertUser(YaUser user);

    String encryptPassword(String password);

    String decryptPassword(String encryptedPassword);

    List<YaUserRole> listRoleById(String userId);

}
