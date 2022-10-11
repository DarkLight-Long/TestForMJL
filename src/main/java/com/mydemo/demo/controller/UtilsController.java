package com.mydemo.demo.controller;

import com.mydemo.demo.domain.SysUser;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/utils")
public class UtilsController {

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/get")
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

    /**
     * 后台重定向
     * @param response
     * @throws IOException
     */
    @GetMapping("/redirect")
    public void redirect(HttpServletResponse response) throws IOException {
        // 1 但是会收不到cookie(疑似因为跨域)
        response.sendRedirect(response.encodeRedirectURL("http://y2lvkry88r3v.ngrok2.xiaomiqiu.cn/test.html"));
        Cookie cookie = new Cookie("code", "code");
        cookie.setDomain("y2lvkry88r3v.ngrok2.xiaomiqiu.cn");
        cookie.setPath("/");
        response.addCookie(cookie);
        response.addHeader("Access-Control-Allow-Credentials", "true");

//        2 解决
//        Cookie cookie = new Cookie("code", "code");
//        cookie.setDomain("y2lvkry88r3v.ngrok2.xiaomiqiu.cn");
//        cookie.setPath("/");
//        response.addCookie(cookie);
//        String html = "<script type='text/javascript'>location.href='" + "http://y2lvkry88r3v.ngrok2.xiaomiqiu.cn/test.html" + "';</script>";
//        response.getWriter().println(html);

    }

    @GetMapping("/getList")
    public void get() {
        System.out.println(httpSession.getId());
        System.out.println(new Date(httpSession.getCreationTime()));
    }

}
