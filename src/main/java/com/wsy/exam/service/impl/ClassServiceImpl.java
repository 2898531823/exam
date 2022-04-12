package com.wsy.exam.service.impl;

import com.wsy.exam.entity.Class;
import com.wsy.exam.mapper.ClassMapper;
import com.wsy.exam.service.IClassService;
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
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements IClassService {

}
