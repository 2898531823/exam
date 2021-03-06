package com.wsy.exam.service;

import com.wsy.exam.common.R;
import com.wsy.exam.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsy.exam.entity.dto.LoginDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wsy
 * @since 2022-04-08
 */
public interface IUserService extends IService<User> {


    /**
     * 登录接口，登录成功返回token
     * @param loginDTO 登录参数
     * @return 成功返回token，失败返回null
     */
    public R login(LoginDTO loginDTO);

    /**
     * 注销接口，注销成功从redis中删除token
     * @return 成功返回信息，失败返回null
     */
    public R logout();

}
