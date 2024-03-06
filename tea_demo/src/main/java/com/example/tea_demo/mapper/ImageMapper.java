package com.example.tea_demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ImageMapper {
    List<String> selectByStoreId(Long storeId);

    List<String> selectByCommodityId(Long commodityId);

}
