package com.example.calculator_server.domain;

import lombok.*;

import java.io.Serializable;
import java.util.Map;

/**
 * (LoanRate)实体类
 *
 * @author bbbbbblack
 * @since 2023-10-21 17:38:35
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoanRate implements Serializable {
    private static final long serialVersionUID = 921972451809212912L;
    /**
     * 6月
     */
    private Double sixMon;
    /**
     * 1年
     */
    private Double oneYear;
    /**
     * 1-3年
     */
    private Double oneToThree;
    /**
     * 3-5年
     */
    private Double threeToFive;
    /**
     * 5年
     */
    private Double fiveYear;

    public static LoanRate mapToLoanRate(Map<String, Object> map) {
        LoanRate loanRate = new LoanRate();
        loanRate.setSixMon(Double.parseDouble(map.get("sixMon").toString()));
        loanRate.setOneYear(Double.parseDouble(map.get("oneYear").toString()));
        loanRate.setOneToThree(Double.parseDouble(map.get("oneToThree").toString()));
        loanRate.setThreeToFive(Double.parseDouble(map.get("threeToFive").toString()));
        loanRate.setFiveYear(Double.parseDouble(map.get("fiveYear").toString()));
        return loanRate;
    }

}

