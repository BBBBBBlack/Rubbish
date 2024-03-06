package com.example.tea_demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tea_demo.domain.ResponseResult;
import com.example.tea_demo.domain.entity.Store;
import com.example.tea_demo.domain.vo.StoreVo;
import com.example.tea_demo.mapper.ImageMapper;
import com.example.tea_demo.mapper.StoreMapper;
import com.example.tea_demo.service.StoreService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

/**
 * (Store)表服务实现类
 *
 * @author bbbbbblack
 * @since 2023-10-08 20:42:49
 */
@Service
public class StoreServiceImpl implements StoreService {
    @Resource
    private StoreMapper storeMapper;
    @Resource
    private ImageMapper imageMapper;

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @Override
    public ResponseResult<IPage<Store>> queryByPage(Long pageNum){
        IPage<Store> page = new Page<>(pageNum,5L);
        page = storeMapper.selectPage(page, null);
        return new ResponseResult<>(200,"查询店铺列表成功",page);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ResponseResult<StoreVo> queryById(Long id) {
        Store store = storeMapper.selectById(id);
        if(store!=null){
            List<String> images_url = imageMapper.selectByStoreId(store.getId());
            StoreVo storeVo=new StoreVo(store);
            storeVo.setImageList(images_url);
            return new ResponseResult<>(200,"查询店铺详细信息",storeVo);
        }
        return new ResponseResult<>(404,"店铺不存在",null);

    }



//    /**
//     * 新增数据
//     *
//     * @param store 实例对象
//     * @return 实例对象
//     */
//    @Override
//    public Store insert(Store store) {
//        this.storeDao.insert(store);
//        return store;
//    }
//
//    /**
//     * 修改数据
//     *
//     * @param store 实例对象
//     * @return 实例对象
//     */
//    @Override
//    public Store update(Store store) {
//        this.storeDao.update(store);
//        return this.queryById(store.getId());
//    }
//
//    /**
//     * 通过主键删除数据
//     *
//     * @param id 主键
//     * @return 是否成功
//     */
//    @Override
//    public boolean deleteById(Long id) {
//        return this.storeDao.deleteById(id) > 0;
//    }
}
