package com.example.tea_demo.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Store)实体类
 *
 * @author bbbbbblack
 * @since 2023-10-08 20:42:45
 */
@Data
@TableName("store")
@AllArgsConstructor
@NoArgsConstructor
public class Store implements Serializable {
    private static final long serialVersionUID = 465533116214534714L;
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

}

