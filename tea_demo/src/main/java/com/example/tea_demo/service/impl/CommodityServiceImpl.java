package com.example.tea_demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tea_demo.domain.ResponseResult;
import com.example.tea_demo.domain.entity.Commodity;
import com.example.tea_demo.domain.vo.CommodityVo;
import com.example.tea_demo.mapper.CommodityMapper;
import com.example.tea_demo.mapper.ImageMapper;
import com.example.tea_demo.service.CommodityService;
import com.example.tea_demo.utils.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Commodity)表服务实现类
 *
 * @author bbbbbblack
 * @since 2023-10-08 22:06:08
 */
@Service("commodityService")
public class CommodityServiceImpl implements CommodityService {
    @Resource
    private CommodityMapper commodityMapper;
    @Resource
    private ImageMapper imageMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ResponseResult<CommodityVo> queryById(Long id) {
        Commodity commodity = commodityMapper.selectById(id);
        if (commodity != null) {
            List<String> imageList = imageMapper.selectByCommodityId(id);
            CommodityVo commodityVo = new CommodityVo(commodity);
            commodityVo.setImageList(imageList);
            return new ResponseResult<>(200, "查询商品详细信息", commodityVo);
        }
        return new ResponseResult<>(404, "商品不存在", null);
    }

    /**
     * 分页查询
     *
     * @param pageNum 分页对象
     * @return 查询结果
     */
    @Override
    public ResponseResult<IPage<Commodity>> queryByPage(Long pageNum) {
        IPage<Commodity> page = new Page<>(pageNum, 5L);
        page = commodityMapper.selectPage(page, null);
        return new ResponseResult<>(200, "查询商品列表", page);
    }

    /**
     * 分页查询
     *
     * @param pageNum 分页对象
     * @return 查询结果
     */
    @Override
    public ResponseResult<IPage<Commodity>> queryByStoreId(Long storeId, Long pageNum) {
        IPage<Commodity> page = new Page<>(pageNum, 5L);
        QueryWrapper<Commodity> wrapper = new QueryWrapper<Commodity>().eq("store_id", storeId);
        page = commodityMapper.selectPage(page, wrapper);
        return new ResponseResult<>(200, "查询店铺商品", page);
    }

    /**
     * 新增数据
     *
     * @param commodity 实例对象
     * @return 实例对象
     */
    @Override
    public ResponseResult<Commodity> addCommodity(Commodity commodity, MultipartFile image) {
        String url = null;
        try {
            url = FileUtil.upLoadProImag(image);
        } catch (Exception e) {
            return new ResponseResult<>(500, e.getMessage());
        }
        commodity.setCoverImage(url);
        commodityMapper.insert(commodity);
        return new ResponseResult<>(200, "新增商品", commodity);
    }
//
//    /**
//     * 修改数据
//     *
//     * @param commodity 实例对象
//     * @return 实例对象
//     */
//    @Override
//    public Commodity update(Commodity commodity) {
//        this.commodityDao.update(commodity);
//        return this.queryById(commodity.getId());
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
//        return this.commodityDao.deleteById(id) > 0;
//    }
}
