package com.wsy.exam.service.impl;

import com.wsy.exam.entity.Menu;
import com.wsy.exam.mapper.MenuMapper;
import com.wsy.exam.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author wsy
 * @since 2022-04-20
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
