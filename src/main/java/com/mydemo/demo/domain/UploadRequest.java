package com.mydemo.demo.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadRequest {

    private MultipartFile file;

}
