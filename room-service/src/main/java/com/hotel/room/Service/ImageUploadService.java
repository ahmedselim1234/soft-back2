package com.hotel.room.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

@Service
public class ImageUploadService {

    private static final List<String> ALLOWED_TYPES =
        List.of("image/jpeg", "image/png", "image/webp", "image/gif");

    private static final String GATEWAY_BASE_URL = "http://localhost:8080";

    @Value("${app.upload-dir}")
    private String uploadDir;

    public String save(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty())
            throw new IllegalArgumentException("File must not be empty");

        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_TYPES.contains(contentType))
            throw new IllegalArgumentException("Only JPEG, PNG, WEBP, GIF are allowed");

        String original = file.getOriginalFilename();
        String ext = (original != null && original.contains("."))
            ? original.substring(original.lastIndexOf('.')) : ".jpg";

        String filename = UUID.randomUUID() + ext;

        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(uploadPath);
        Files.copy(file.getInputStream(), uploadPath.resolve(filename), StandardCopyOption.REPLACE_EXISTING);

        return GATEWAY_BASE_URL + "/rooms/uploads/" + filename;
    }
}
