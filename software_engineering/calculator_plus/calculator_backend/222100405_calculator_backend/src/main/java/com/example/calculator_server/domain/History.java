package com.example.calculator_server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (History)实体类
 *
 * @author bbbbbblack
 * @since 2023-10-22 04:16:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class History implements Serializable {
    private static final long serialVersionUID = 731268983449111638L;

    private Integer id;
    /**
     * 历史记录
     */
    private String history;
    /**
     * 结果
     */
    private Double result;

}

