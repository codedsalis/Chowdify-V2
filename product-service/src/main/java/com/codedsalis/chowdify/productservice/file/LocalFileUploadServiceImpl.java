package com.codedsalis.chowdify.productservice.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class LocalFileUploadServiceImpl implements FileUploadService {
    @Override
    public UploadResult upload(MultipartFile file) {
        if (file.isEmpty()) {
            return new UploadResult(false, "Invalid file", (Map<?, ?>) new HashMap<>().put("error", true));
        }

        try {
            byte[] bytes = file.getBytes();
            File fileToSave = new File("uploads/" + file.getOriginalFilename());
            file.transferTo(fileToSave);

            Map<String, Object> fileDetails = new HashMap<>();
            fileDetails.put("url", "");
            fileDetails.put("public_id", file.getName());

            return new UploadResult(true, "File uploaded", fileDetails);
        } catch (IOException e) {
            return new UploadResult(false, e.getMessage(), (Map<?, ?>) new HashMap<>().put("error", true));
        }
    }
}
