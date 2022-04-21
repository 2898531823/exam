package com.wsy.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wsy.exam.entity.Role;
import com.wsy.exam.entity.UserRole;
import com.wsy.exam.mapper.RoleMapper;
import com.wsy.exam.mapper.UserRoleMapper;
import com.wsy.exam.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wsy
 * @since 2022-04-20
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public List<UserRole> getListByUserName(String username) {
        return userRoleMapper.selectList(new QueryWrapper<UserRole>().eq("user_id",username));
    }

}
