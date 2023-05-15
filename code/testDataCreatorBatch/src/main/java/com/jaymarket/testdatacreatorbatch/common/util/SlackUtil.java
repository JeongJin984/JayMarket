package com.jaymarket.testdatacreatorbatch.common.util;

import com.jaymarket.testdatacreatorbatch.common.dto.SlackMessageBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

@Slf4j
@RequiredArgsConstructor
@Component
public class SlackUtil {
    public static CompletableFuture<String> sendMessage(String url, SlackMessageBody body) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            String result;
            try {
                result = HttpUtil.post(url, body);
                log.info("Slack 요청 결과: " + result);
            } catch (IOException e) {
                log.error("Http 요청 실패");
                throw new RuntimeException(e);
            }
            return result;
        });
        return future;
    }
}
