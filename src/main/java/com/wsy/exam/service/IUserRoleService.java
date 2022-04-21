package com.wsy.exam.service;

import com.wsy.exam.entity.Role;
import com.wsy.exam.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wsy
 * @since 2022-04-20
 */
public interface IUserRoleService extends IService<UserRole> {

    /**
     * 查询用户角色
     * @param username 用户名
     * @return List<Role>
     */
    List<UserRole> getListByUserName(String username);

}
