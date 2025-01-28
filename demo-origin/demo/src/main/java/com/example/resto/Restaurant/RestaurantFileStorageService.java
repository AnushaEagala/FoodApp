package com.example.resto.Restaurant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static java.io.File.separator;
import static java.lang.System.currentTimeMillis;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantFileStorageService {

    @Value("${application.file.uploads.photos-output-path}")
    private String fileUploadPath;

    public String saveFile(MultipartFile sourceFile, Long restaurantId) {
        final String fileUploadSubPath = "resto" + separator + restaurantId;
        return uploadFile(sourceFile, fileUploadSubPath);
    }

    private String uploadFile(MultipartFile sourceFile, String fileUploadSubPath) {
        final String finalUploadPath = fileUploadPath + separator + fileUploadSubPath;
        File targetFolder = new File(finalUploadPath);

        if (!targetFolder.exists() && !targetFolder.mkdirs()) {
            log.warn("Failed to create the target folder: {}", targetFolder);
            return null;
        }

        String fileExtension = getFileExtension(sourceFile.getOriginalFilename());
        String targetFilePath = finalUploadPath + separator + currentTimeMillis() + "." + fileExtension;
        Path targetPath = Paths.get(targetFilePath);

        try {
            Files.copy(sourceFile.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            log.info("File saved to: {}", targetFilePath);
            return targetFilePath;
        } catch (IOException e) {
            log.error("Failed to save file: {}", targetFilePath, e);
            return null;
        }
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }
        int lastDotIndex = fileName.lastIndexOf(".");
        return lastDotIndex == -1 ? "" : fileName.substring(lastDotIndex + 1).toLowerCase();
    }
}
