package com.example.tea_demo.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {
    void addRole(Long userId, Long roleId);
}
