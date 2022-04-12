package com.wsy.exam.service.impl;

import com.wsy.exam.entity.Role;
import com.wsy.exam.mapper.RoleMapper;
import com.wsy.exam.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wsy
 * @since 2022-04-08
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
