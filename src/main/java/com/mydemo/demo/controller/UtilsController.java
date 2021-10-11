package com.mydemo.demo.controller;

import com.mydemo.demo.domain.SysUser;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController("/utils")
public class UtilsController {

    @PostMapping("/get")
    public void getRestTemplate() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<SysUser[]> responseEntity = template.getForEntity("http://192.168.0.29:8090/sys/select", SysUser[].class);
        SysUser[] sysUsers = responseEntity.getBody();
    }

    @PostMapping("/get")
    public void getHttpClientUtil() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        HttpGet get = new HttpGet();
        httpResponse = httpClient.execute(get);
    }

}
