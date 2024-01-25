package com.example.calculator_server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * (DepRate)实体类
 *
 * @author bbbbbblack
 * @since 2023-10-21 17:37:53
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepRate implements Serializable {
    private static final long serialVersionUID = 472316695306302782L;
    /**
     * 活期存款
     */
    private Double demandDep;
    /**
     * 三个月
     */
    private Double threeMon;
    /**
     * 半年
     */
    private Double sixMon;
    /**
     * 1年
     */
    private Double oneYear;
    /**
     * 2年
     */
    private Double twoYear;
    /**
     * 3年
     */
    private Double threeYear;
    /**
     * 5年
     */
    private Double fiveYear;

    public static DepRate mapToDepRate(Map<String, Object> map) {
        DepRate depRate = new DepRate();
        depRate.setDemandDep(Double.parseDouble(map.get("demandDep").toString()));
        depRate.setThreeMon(Double.parseDouble(map.get("threeMon").toString()));
        depRate.setSixMon(Double.parseDouble(map.get("sixMon").toString()));
        depRate.setOneYear(Double.parseDouble(map.get("oneYear").toString()));
        depRate.setTwoYear(Double.parseDouble(map.get("twoYear").toString()));
        depRate.setThreeYear(Double.parseDouble(map.get("threeYear").toString()));
        depRate.setFiveYear(Double.parseDouble(map.get("fiveYear").toString()));
        return depRate;
    }

}

