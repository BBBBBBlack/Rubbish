package com.example.tea_demo.domain.vo;

import com.example.tea_demo.domain.entity.Commodity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CommodityVo implements Serializable {
    private static final long serialVersionUID = 4534856923875L;

    private Long id;

    private Long storeId;

    private String name;

    private Double price;

    private String link;

    private String description;

    private String coverImage;

    private List<String> imageList;

    public CommodityVo(Commodity commodity){
        this.id = commodity.getId();
        this.storeId = commodity.getStoreId();
        this.name = commodity.getName();
        this.price = commodity.getPrice();
        this.link = commodity.getLink();
        this.description = commodity.getDescription();
        this.coverImage = commodity.getCoverImage();
    }
}
