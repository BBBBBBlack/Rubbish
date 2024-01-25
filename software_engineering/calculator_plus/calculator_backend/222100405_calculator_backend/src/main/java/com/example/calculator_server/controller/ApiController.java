package com.example.calculator_server.controller;

import com.example.calculator_server.domain.DepRate;
import com.example.calculator_server.domain.LoanRate;
import com.example.calculator_server.domain.ResponseResult;
import com.example.calculator_server.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    ApiService apiService;
    @GetMapping("/getRate")
    public Double getRate(@RequestParam Integer rateType,
                          @RequestParam Integer storeTime,
                          @RequestParam Integer money) {
        return apiService.getRate(rateType, storeTime, money);
    }

    @GetMapping("/getRateTable")
    public ResponseResult getRateTable(@RequestParam Integer rateType) {
        return apiService.getRateTable(rateType);
    }

    @PostMapping("/updateRateTable")
    public ResponseResult updateRateTable(@RequestBody Map<String, Object> map) {
        Integer rateType = (Integer) map.get("rateType");
        LinkedHashMap<String, Object> rateTable = (LinkedHashMap<String, Object>) map.get("rateTable");
        return apiService.updateRateTable(rateType, rateTable);
    }

    @PostMapping("/addHistory")
    public ResponseResult addHistory(@RequestParam String expression) {
        return apiService.addHistory(expression);
    }
    @GetMapping("/getHistory")
    public ResponseResult getHistory() {
        return apiService.getHistory();
    }
}
