package com.ya.boottest.manage.user.entity;

import com.ya.boottest.autocode.user.entity.YaUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 *  map struct mapper
 * </p>
 *
 * @author Ya Shi
 * @since 2024/3/18 10:22
 */
@Mapper
public interface UserRegisterMapper {
    UserRegisterMapper INSTANCE = Mappers.getMapper(UserRegisterMapper.class);

    UserRegisterDTO userToRegister(YaUser yaUser);

    YaUser registerToUser(UserRegisterDTO userRegisterDTO);
}
