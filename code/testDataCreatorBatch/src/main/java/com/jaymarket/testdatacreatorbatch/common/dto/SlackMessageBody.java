package com.jaymarket.testdatacreatorbatch.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlackMessageBody {
    private String channel;
    private String username;
    private String icon_emoji;
    private String text;
}
