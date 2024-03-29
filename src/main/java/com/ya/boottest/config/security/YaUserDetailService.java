package com.ya.boottest.config.security;

import com.ya.boottest.autocode.user.entity.YaUser;
import com.ya.boottest.manage.user.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  继承UserDetailsService,实现自定义登陆认证
 * </p>
 *
 * @author Ya Shi
 * @since 2024/3/19 11:32
 */
@Service
public class YaUserDetailService implements UserDetailsService {

    @Autowired
    UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        YaUser user = userService.getUserById(username);
        if(Objects.isNull(user)){
            throw new UsernameNotFoundException("User not Found.");
        }
        List<String> roles = userService.listAuthorityById(username);
        List<GrantedAuthority> authorities = new ArrayList<>(roles.size());
        roles.forEach( item -> authorities.add(new SimpleGrantedAuthority(item)));

        return new User(username, user.getUserPassword(), authorities);
    }
}
