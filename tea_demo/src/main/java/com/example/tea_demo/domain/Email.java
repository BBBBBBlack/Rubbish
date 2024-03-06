package com.example.tea_demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    private String from;
    private String to;
    private String subject;
    private String text;
}
