package com.example.data.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.example.data.annotation.FieldParam;
import com.example.data.annotation.TranslationField;
import com.example.data.demo.domain.entity.UserEntity;
import com.example.data.demo.mapper.UserMapper;
import com.example.data.demo.domain.query.UserQuery;
import com.example.data.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    @TranslationField({
            @FieldParam(code = "gender", sourceFiled = "gender", targetFiled = "genderName"),
            @FieldParam(code = "status", sourceFiled = "status", targetFiled = "statusName")
    })
    public List<UserEntity> list(UserQuery query) {
        LambdaQueryWrapper<UserEntity> wrapper = Wrappers.<UserEntity>lambdaQuery();
        wrapper.eq(StringUtils.isNotBlank(query.getUsername()),UserEntity::getUsername, query.getUsername());
        wrapper.orderByAsc(UserEntity::getAge);
        return userMapper.selectList(wrapper);
    }

    @Override
    public List<UserEntity> list2(UserQuery query) {
        return new LambdaQueryChainWrapper<>(userMapper)
                .like(UserEntity::getName, "雨").ge(UserEntity::getAge, 20).list();
    }

    @Override
    @TranslationField
    public UserEntity detail(String id) {
        return this.userMapper.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(UserEntity entity) {
        this.userMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UserEntity entity) {
        this.userMapper.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        this.userMapper.deleteById(id);
    }
}
