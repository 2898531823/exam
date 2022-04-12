package com.wsy.exam.service.impl;

import com.wsy.exam.entity.Exam;
import com.wsy.exam.mapper.ExamMapper;
import com.wsy.exam.service.IExamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 考试的详细信息表 服务实现类
 * </p>
 *
 * @author wsy
 * @since 2022-04-04
 */
@Service
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam> implements IExamService {

}
