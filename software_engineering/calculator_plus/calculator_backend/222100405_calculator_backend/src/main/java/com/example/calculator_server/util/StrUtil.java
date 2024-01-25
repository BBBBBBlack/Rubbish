package com.example.calculator_server.util;

public class StrUtil {
    public static String camelToUnderscore(String str) {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        return str.replaceAll(regex, replacement).toLowerCase();
    }
}
