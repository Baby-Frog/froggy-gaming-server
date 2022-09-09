package com.forggygaming.froggygamingserver.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.stream.Stream;
@Service
@Slf4j

public class ImgServicesImpl implements ImgServices{
    private final Path storageFolder= Paths.get("uploads");

    public ImgServicesImpl() {
        try {
            Files.createDirectories(storageFolder);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
    private boolean isImgFile(MultipartFile file){
        String fileExtension= FilenameUtils.getExtension(file.getOriginalFilename());
        return Arrays.asList(new String[]{"png","jpg","jpeg","bmp"})
                .contains(fileExtension.trim().toLowerCase());
    }

    @Override
    public String storeFile(MultipartFile file) {
        try {
            if (file.isEmpty()){
                throw new RuntimeException("Failed to store empty file");
            }
            if (!isImgFile(file)){
                throw new RuntimeException("You can only upload image file");

            }
            float fileSizeInMegabyte= file.getSize() / 1_000_000.0f;
            if (fileSizeInMegabyte>5.0f){
                throw new RuntimeException("File must be <=5MB");
            }
//            String fileExtension=FilenameUtils.getExtension(file.getOriginalFilename());
            String generatedFileName= file.getOriginalFilename().replace(" ","").replace("-","").replace("(","").replace(")","");
            Path destinationFilePath=this.storageFolder.resolve(Paths.get(generatedFileName)).normalize().toAbsolutePath();
            if (!destinationFilePath.getParent().equals(this.storageFolder.toAbsolutePath())){
                throw new RuntimeException("Can not store file outside current directory");
            }
            try (InputStream inputStream=file.getInputStream()){
                Files.copy(inputStream,destinationFilePath, StandardCopyOption.REPLACE_EXISTING);

            }
            return generatedFileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file"+e);
        }

    }

    @Override
    public Stream<Path> loadAll() {
        try{
            return Files.walk(this.storageFolder,1).filter(path -> !path.equals(this.storageFolder)).map(this.storageFolder::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load stored files");
        }
    }


    @Override
    public byte[] readFileContent(String fileName) {
       try {
           Path file=storageFolder.resolve(fileName);
           Resource resource=new UrlResource(file.toUri());
           if (resource.exists()||resource.isReadable()){
               byte[] bytes= StreamUtils.copyToByteArray(resource.getInputStream());
               return bytes;
           }else {
               throw new RuntimeException("Could not read file"+fileName);
           }

       } catch (IOException e) {
           throw new RuntimeException("Could not read file"+fileName,e);

       }
    }

    @Override
    public boolean deleteFile(String fileName) {
       try {
           Path path=storageFolder.resolve(fileName);
           File file =new File(path.toUri());
           file.delete();
           return true;

       } catch (Exception e) {
          log.error("Error: "+e.getMessage() );
          return false;
       }



    }
}
