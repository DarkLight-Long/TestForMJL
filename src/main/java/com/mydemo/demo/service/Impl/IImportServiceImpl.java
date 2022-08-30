package com.mydemo.demo.service.Impl;

import com.mydemo.demo.domain.UploadRequest;
import com.mydemo.demo.service.IImportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Map;

@Slf4j
@Service
public class IImportServiceImpl implements IImportService {

    @Override
    public void uploadFile(UploadRequest request, Integer size) {
        try {
            long startTime = System.currentTimeMillis();
            File file = this.transformToFile(request.getFile(), size);
            System.out.println(System.currentTimeMillis() - startTime);
            if (file.exists()) {
                System.out.println(file.delete());
            }

            RestTemplate restTemplate = new RestTemplate();
            MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
            map.add("file", new FileSystemResource(file));

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());
            httpHeaders.setContentType(MediaType.parseMediaType("multipart/form-data; charset=UTF-8"));
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(map, httpHeaders);
            restTemplate.patchForObject("", httpEntity, Map.class);
        } catch (Exception e) {
            log.error("转换失败", e);
        }
    }

    private File transformToFile(MultipartFile multipartFile, int byteSize) throws IOException {
        File file = new File("./src/test.jpg");
        InputStream inputStream = multipartFile.getInputStream();
        OutputStream outputStream = new FileOutputStream(file);

        if (byteSize == 0) {
            byteSize = 1024;
        }// 1024 8192
        byte[] bytes = new byte[byteSize];
        while (inputStream.read(bytes) >= 0) {
            outputStream.write(bytes);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        return file;
    }
}
