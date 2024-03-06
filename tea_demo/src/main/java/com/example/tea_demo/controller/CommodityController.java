package com.example.tea_demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.tea_demo.domain.ResponseResult;
import com.example.tea_demo.domain.entity.Commodity;
import com.example.tea_demo.domain.vo.CommodityVo;
import com.example.tea_demo.service.CommodityService;
import com.example.tea_demo.utils.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * (Commodity)表控制层
 *
 * @author bbbbbblack
 * @since 2023-10-08 22:06:05
 */
@RestController
@RequestMapping("/commodity")
public class CommodityController {
    /**
     * 服务对象
     */
    @Resource
    private CommodityService commodityService;

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @GetMapping("/queryByPage/{pageNum}")
    public ResponseResult<IPage<Commodity>> queryByPage(@PathVariable Long pageNum) {
        return commodityService.queryByPage(pageNum);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/queryById/{id}")
    public ResponseResult<CommodityVo> queryById(@PathVariable("id") Long id) {
        return commodityService.queryById(id);
    }

    /**
     * 通过商店id查询商品
     *
     * @param storeId 商店id
     * @param pageNum 分页对象
     * @return 查询结果
     */
    @GetMapping("/queryByStoreId/{storeId}/{pageNum}")
    public ResponseResult<IPage<Commodity>> queryByStoreId(
            @PathVariable("storeId") Long storeId,
            @PathVariable("pageNum") Long pageNum) {
        return commodityService.queryByStoreId(storeId, pageNum);
    }

    /**
     * 新增数据
     *
     * @return 新增结果
     */
    @PostMapping("/addCommodity")
    public ResponseResult<Commodity> addCommodity(@RequestParam String name,
                                                  @RequestParam Double price,
                                                  @RequestParam String link,
                                                  @RequestParam String description,
                                                  @RequestParam MultipartFile image) {
        Commodity commodity = new Commodity(null, SecurityUtil.getNowUserId(), name, price, link, description, null);
        return commodityService.addCommodity(commodity, image);
    }
//
//    /**
//     * 编辑数据
//     *
//     * @param commodity 实体
//     * @return 编辑结果
//     */
//    @PutMapping
//    public ResponseEntity<Commodity> edit(Commodity commodity) {
//        return ResponseEntity.ok(this.commodityService.update(commodity));
//    }
//
//    /**
//     * 删除数据
//     *
//     * @param id 主键
//     * @return 删除是否成功
//     */
//    @DeleteMapping
//    public ResponseEntity<Boolean> deleteById(Long id) {
//        return ResponseEntity.ok(this.commodityService.deleteById(id));
//    }

}

