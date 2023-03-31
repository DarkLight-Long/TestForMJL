package com.mydemo.demo.service;

import com.mydemo.demo.domain.UploadRequest;

public interface ImportService {
    void uploadFile(UploadRequest request, Integer size);
}
