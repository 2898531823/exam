package com.wsy.exam.mapper;

import com.wsy.exam.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author wsy
 * @since 2022-04-20
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户名获取权限集合
     * @param username 用户名
     * @return List<String> 权限集合
     */
    @Select("select\n" +
            "DISTINCT m.perms \n" +
            "FROM user_role ur\n" +
            "LEFT JOIN role_menu rm ON ur.role_id = rm.role_id\n" +
            "LEFT JOIN menu m ON m.id = rm.menu_id\n" +
            "WHERE user_id = #{username} and\n" +
            "m.perms is not null")
    List<String> selectPermsByUserId(String username);

}
