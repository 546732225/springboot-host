package com.example.data.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.data.demo.domain.entity.RegionEntity;
import com.example.data.demo.service.RegionEntityService;
import com.example.data.demo.mapper.RegionEntityMapper;
import org.springframework.stereotype.Service;

/**
* @author seven
* @description 针对表【dic_region_zbi_all】的数据库操作Service实现
* @createDate 2022-03-24 17:01:50
*/
@Service
public class RegionEntityServiceImpl extends ServiceImpl<RegionEntityMapper, RegionEntity>
    implements RegionEntityService{

}




