package com.forggygaming.froggygamingserver.service;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface ImgServices {
    String storeFile(MultipartFile file);
    Stream<Path> loadAll();
    byte[] readFileContent(String fileName);
    void deleteAllFile();
}
