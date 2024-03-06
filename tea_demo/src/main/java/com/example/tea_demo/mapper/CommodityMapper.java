package com.example.tea_demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tea_demo.domain.entity.Commodity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommodityMapper extends BaseMapper<Commodity> {
}
