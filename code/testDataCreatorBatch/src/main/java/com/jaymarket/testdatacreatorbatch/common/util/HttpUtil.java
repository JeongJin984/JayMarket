package com.jaymarket.testdatacreatorbatch.common.util;

import org.springframework.stereotype.Component;

@Component
public class HttpUtil {
    public String download(String currentDate) {
        return currentDate + "1357.csv";
    }
}
