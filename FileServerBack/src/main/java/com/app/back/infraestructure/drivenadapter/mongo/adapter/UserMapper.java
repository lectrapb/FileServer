package com.app.back.infraestructure.drivenadapter.mongo.adapter;

import com.app.back.domain.model.user.User;
import com.app.back.infraestructure.drivenadapter.mongo.entity.UserEntity;
import org.springframework.beans.BeanUtils;

public class UserMapper {

    public static User toModel(UserEntity userEntity){
        User user = new User();
        BeanUtils.copyProperties(userEntity,user);
        return user;
    }

    public static UserEntity toData(User user){
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user,userEntity);
        return userEntity;
    }
}
