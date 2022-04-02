package com.example.data.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.data.annotation.DictParam;
import com.example.data.annotation.TranslationDict;
import com.example.data.demo.domain.UserEntity;
import com.example.data.demo.mapper.UserMapper;
import com.example.data.demo.query.UserQuery;
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
    @TranslationDict({
            @DictParam(dictCode = "gender", dictValueFiled = "gender", dictNameFiled = "genderName"),
            @DictParam(dictCode = "status", dictValueFiled = "status", dictNameFiled = "statusName")
    })
    public List<UserEntity> list(UserQuery query) {
        LambdaQueryWrapper<UserEntity> wrapper = Wrappers.<UserEntity>lambdaQuery();
        wrapper.eq(StringUtils.isNotBlank(query.getUsername()),UserEntity::getUsername, query.getUsername());
        wrapper.orderByAsc(UserEntity::getAge);
        return userMapper.selectList(wrapper);
    }

    @Override
    @TranslationDict({
            @DictParam(dictCode = "gender", dictValueFiled = "gender", dictNameFiled = "genderName"),
            @DictParam(dictCode = "status", dictValueFiled = "status", dictNameFiled = "statusName")
    })
    @Transactional(rollbackFor = Exception.class)
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
