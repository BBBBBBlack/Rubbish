package com.example.tea_demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.tea_demo.domain.ResponseResult;
import com.example.tea_demo.domain.entity.Store;
import com.example.tea_demo.domain.vo.StoreVo;

/**
 * (Store)表服务接口
 *
 * @author bbbbbblack
 * @since 2023-10-08 20:42:49
 */
public interface StoreService {
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    ResponseResult<IPage<Store>> queryByPage(Long pageNum);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ResponseResult<StoreVo> queryById(Long id);



//    /**
//     * 新增数据
//     *
//     * @param store 实例对象
//     * @return 实例对象
//     */
//    Store insert(Store store);
//
//    /**
//     * 修改数据
//     *
//     * @param store 实例对象
//     * @return 实例对象
//     */
//    Store update(Store store);
//
//    /**
//     * 通过主键删除数据
//     *
//     * @param id 主键
//     * @return 是否成功
//     */
//    boolean deleteById(Long id);

}
