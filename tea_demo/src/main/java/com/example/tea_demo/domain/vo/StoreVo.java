package com.example.tea_demo.domain.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.tea_demo.domain.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class StoreVo implements Serializable {
    private static final long serialVersionUID = 92375683465934L;
    /**
     * 店铺id
     */
    private Long id;
    /**
     * 店铺名
     */
    private String name;

    private String link;

    private String description;

    private List<String> imageList;

    public StoreVo(Store store){
        this.id = store.getId();
        this.name = store.getName();
        this.link = store.getLink();
        this.description = store.getDescription();
    }

}