package com.ya.boottest.autocode.user.service.impl;

import com.ya.boottest.autocode.user.entity.YaUser;
import com.ya.boottest.autocode.user.mapper.YaUserMapper;
import com.ya.boottest.autocode.user.service.IYaUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Ya Shi
 * @since 2023-08-12
 */
@Service
public class YaUserServiceImpl extends ServiceImpl<YaUserMapper, YaUser> implements IYaUserService {

}
