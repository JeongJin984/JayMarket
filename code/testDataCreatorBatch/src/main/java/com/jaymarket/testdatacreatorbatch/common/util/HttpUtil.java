package com.jaymarket.testdatacreatorbatch.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

@Component
public class HttpUtil {

    public String download(String currentDate) {
        return currentDate + "1357.csv";
    }

    public static String post(String url, Object body) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("POST"); // 전송 방식
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        conn.setConnectTimeout(5000); // 연결 타임아웃 설정(5초)
        conn.setReadTimeout(5000); // 읽기 타임아웃 설정(5초)
        conn.setDoOutput(true);	// URL 연결을 출력용으로 사용(true)

        String requestBody = objectMapper.writeValueAsString(body);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        bw.write(requestBody);
        bw.flush();
        bw.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), Charset.defaultCharset()));

        String inputLine;
        StringBuilder sb = new StringBuilder();
        while ((inputLine = br.readLine()) != null) {
            sb.append(inputLine);
        }
        br.close();

        return sb.toString();
    }
}
