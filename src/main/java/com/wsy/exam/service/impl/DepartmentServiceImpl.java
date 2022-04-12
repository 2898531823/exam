package com.wsy.exam.service.impl;

import com.wsy.exam.entity.Department;
import com.wsy.exam.mapper.DepartmentMapper;
import com.wsy.exam.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wsy
 * @since 2022-04-04
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}
