package com.example.data.demo.service;

import com.example.data.demo.domain.entity.UserEntity;
import com.example.data.demo.domain.query.UserQuery;

import java.util.List;

public interface UserService {




    List<UserEntity> list(UserQuery query);

    List<UserEntity> list2(UserQuery query);



    UserEntity detail(String id);

    void insert(UserEntity entity);

    void update(UserEntity entity);

    void delete(String id);

}
