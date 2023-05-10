package com.jaymarket.testdatacreatorbatch.common.util;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateUtil {
    public String currentDate() {
        return new Date().toString();
    }
}
