package com.example.tea_demo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Commodity)实体类
 *
 * @author bbbbbblack
 * @since 2023-10-08 22:06:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commodity implements Serializable {
    private static final long serialVersionUID = 777351128680380470L;
    
    private Long id;
    
    private Long storeId;
    
    private String name;
    
    private Double price;
    
    private String link;
    
    private String description;

    private String coverImage;

}

