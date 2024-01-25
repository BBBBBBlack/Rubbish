package com.example.calculator_server.mapper;

import com.example.calculator_server.domain.DepRate;
import com.example.calculator_server.domain.History;
import com.example.calculator_server.domain.LoanRate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApiMapper {
    List<DepRate> getDepRateTable();

    List<LoanRate> getLoanRateTable();

    void updateDepRateTable(DepRate depRate);

    void updateLoanRateTable(LoanRate loanRate);

    void addHistory(String expression, Double result);

    List<History> getHistory();
}
