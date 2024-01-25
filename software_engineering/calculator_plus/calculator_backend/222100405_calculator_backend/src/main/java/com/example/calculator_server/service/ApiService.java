package com.example.calculator_server.service;

import com.example.calculator_server.domain.ResponseResult;

import java.util.LinkedHashMap;

public interface ApiService {
    ResponseResult getRateTable(Integer rateType);

    ResponseResult updateRateTable(Integer rateType, LinkedHashMap<String, Object> rateTable);

    ResponseResult addHistory(String expression);

    ResponseResult getHistory();

    Double getRate(Integer rateType, Integer storeTime, Integer money);
}
