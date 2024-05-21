package com.codedsalis.chowdify.productservice.file;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public record UploadResult(Boolean status, String message, Map<?, ?> data) {
}
