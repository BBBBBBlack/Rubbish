package com.example.tea_demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.tea_demo.domain.ResponseResult;
import com.example.tea_demo.domain.entity.Commodity;
import com.example.tea_demo.domain.entity.Store;
import com.example.tea_demo.domain.vo.StoreVo;
import com.example.tea_demo.service.StoreService;
import com.example.tea_demo.utils.SecurityUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Store)表控制层
 *
 * @author bbbbbblack
 * @since 2023-10-08 20:42:45
 */
@RestController
@RequestMapping("/store")
public class StoreController {
    /**
     * 服务对象
     */
    @Resource
    private StoreService storeService;

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @GetMapping("/queryByPage/{pageNum}")
    public ResponseResult<IPage<Store>> queryByPage(@PathVariable Long pageNum) {
        return storeService.queryByPage(pageNum);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/queryById/{id}")
    public ResponseResult<StoreVo> queryById(@PathVariable("id") Long id) {
        return storeService.queryById(id);
    }

//    /**
//     * 新增数据
//     *
//     * @param store 实体
//     * @return 新增结果
//     */
//    @PostMapping
//    public ResponseEntity<Store> add(@RequestParam String name,
//                                     @RequestParam Double price,
//                                     @RequestParam String link,
//                                     @RequestParam String description) {
//        Commodity commodity = new Commodity(null, SecurityUtil.getNowUserId(), name, price, link, description, null);
//        return ResponseEntity.ok(this.storeService.insert(store));
//    }
//
//    /**
//     * 编辑数据
//     *
//     * @param store 实体
//     * @return 编辑结果
//     */
//    @PutMapping
//    public ResponseEntity<Store> edit(Store store) {
//        return ResponseEntity.ok(this.storeService.update(store));
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
//        return ResponseEntity.ok(this.storeService.deleteById(id));
//    }

}

