package com.codedsalis.chowdify.productservice.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {
    UploadResult upload(MultipartFile file);
}
