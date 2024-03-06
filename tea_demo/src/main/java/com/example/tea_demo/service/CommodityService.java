package com.example.tea_demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tea_demo.domain.ResponseResult;
import com.example.tea_demo.domain.entity.Commodity;
import com.example.tea_demo.domain.vo.CommodityVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * (Commodity)表服务接口
 *
 * @author bbbbbblack
 * @since 2023-10-08 22:06:08
 */
public interface CommodityService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ResponseResult<CommodityVo> queryById(Long id);

    /**
     * 分页查询
     *
     * @param pageNum 分页对象
     * @return 查询结果
     */
    ResponseResult<IPage<Commodity>> queryByPage(Long pageNum);

    /**
     * 分页查询
     *
     * @param pageNum 分页对象
     * @return 查询结果
     */
    ResponseResult<IPage<Commodity>> queryByStoreId(Long storeId, Long pageNum);

    /**
     * 新增数据
     *
     * @param commodity 实例对象
     * @return 实例对象
     */
    ResponseResult<Commodity> addCommodity(Commodity commodity, MultipartFile image);
//
//    /**
//     * 修改数据
//     *
//     * @param commodity 实例对象
//     * @return 实例对象
//     */
//    Commodity update(Commodity commodity);
//
//    /**
//     * 通过主键删除数据
//     *
//     * @param id 主键
//     * @return 是否成功
//     */
//    boolean deleteById(Long id);

}
