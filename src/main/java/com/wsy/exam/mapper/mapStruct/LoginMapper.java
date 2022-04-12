package com.wsy.exam.mapper.mapStruct;

import com.wsy.exam.entity.User;
import com.wsy.exam.entity.dto.LoginDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


/**
 * @className: com.wsy.exam.mapper-> LoginMapper
 * @description: User->LoginDTO
 * @author: wsy
 * @createDate: 2022-04-06 19:36
 * @version: 1.0
 */

@Mapper
public interface LoginMapper {

    LoginMapper INSTANCE = Mappers.getMapper(LoginMapper.class);

    @Mapping(target = "username",source = "user.id")
    LoginDTO toDto (User user);


}
