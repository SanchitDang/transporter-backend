package com.transporterbackend.Utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.UUID;

public class FileService {

    public String uploadImage(String path, MultipartFile file) throws IOException {
        // Generate a random unique filename using UUID
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        String randomFileName = UUID.randomUUID().toString() + extension;

        // Construct the file path with the random filename
        String filePath = path + File.separator + randomFileName;

        // Create the directory if it does not exist
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs(); // Use mkdirs() to create parent directories if needed
        }

        // Copy the file to the specified path
        Files.copy(file.getInputStream(), Paths.get(filePath));

        // Return the file path with the random filename
        return filePath;
    }

    public void removeImage(String filepath) throws IOException {
        // Construct Path object from filepath
        Path path = Paths.get(filepath);

        // Delete the file
        Files.deleteIfExists(path);
    }

}
