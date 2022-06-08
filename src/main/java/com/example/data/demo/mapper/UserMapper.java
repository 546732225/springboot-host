package com.example.data.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.data.demo.domain.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
