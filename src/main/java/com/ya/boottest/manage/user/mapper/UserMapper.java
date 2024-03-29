package com.ya.boottest.manage.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author Ya Shi
 * @since 2024/3/28 17:26
 */
@Mapper
public interface UserMapper {

    List<String>  listAuthorityById(String userId);
}
