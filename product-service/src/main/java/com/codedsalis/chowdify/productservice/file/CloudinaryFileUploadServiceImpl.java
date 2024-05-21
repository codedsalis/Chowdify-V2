package com.codedsalis.chowdify.productservice.file;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Primary
@Slf4j
public class CloudinaryFileUploadServiceImpl implements FileUploadService {

    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryFileUploadServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public UploadResult upload(MultipartFile file) {
        Map params = ObjectUtils.asMap(
                "use_filename", true,
                "unique_filename", true,
                "overwrite", false,
                "folder", "chowdify/products"
        );

        try {
            Map result = cloudinary.uploader().upload(file.getBytes(), params);

            return new UploadResult(true, "File uploaded successfully", result);
        } catch (IOException e) {
            log.error(e.toString());
            return new UploadResult(false, "File upload failed", (Map<?, ?>) new HashMap<>().put("error", true));
        }
    }
}
