package com.wsy.exam.entity.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wsy.exam.entity.Role;
import com.wsy.exam.utils.CustomAuthorityDeserializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @className: com.wsy.exam.entity.dto-> LoginDTO
 * @description:登录数据传输模型
 * @author: wsy
 * @createDate: 2022-04-04 23:28
 * @version: 1.0
 * @todo:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("登录数据传输模型")
public class LoginDTO implements UserDetails {

    @ApiModelProperty("学号")
    private String username;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("用户角色")
    private String role;

    @ApiModelProperty("记住我")
    private Boolean rememberMe;

    Collection<? extends GrantedAuthority> authorities;

    public LoginDTO(String username, String password, String role, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.authorities = authorities;
    }

    @Override
    @JsonDeserialize(using = CustomAuthorityDeserializer.class)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+this.role);
        authorities.add(authority);
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
