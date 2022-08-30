package com.mydemo.demo.service;

import com.mydemo.demo.domain.UploadRequest;

public interface IImportService {
    void uploadFile(UploadRequest request, Integer size);
}
