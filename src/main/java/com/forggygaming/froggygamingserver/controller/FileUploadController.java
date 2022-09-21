package com.forggygaming.froggygamingserver.controller;


import com.forggygaming.froggygamingserver.entity.Image;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.service.ImageServices;
import com.forggygaming.froggygamingserver.service.ImgServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/fileupload")
@CrossOrigin("http://localhost:3000")

@Slf4j
public class FileUploadController {
    @Autowired
    private ImgServices services;
    @Autowired
    private ImageServices imageServices;
    @PostMapping()

    public ResponseEntity<ResponseObject>uploadfile(@RequestParam("file")MultipartFile file){
        try {
            String generatedFileName=services.storeFile(file);
            if (!generatedFileName.isEmpty()){
                Image image=new Image();

                image.setImgName(file.getOriginalFilename());
                image.setImgPath("http://localhost:8386/api/v1/fileupload/file/"+file.getOriginalFilename());
                imageServices.saveNewImage(image);
            }
            return ResponseEntity.ok().body(
                    new ResponseObject("ok","upload successfully",generatedFileName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("ok",e.getMessage(),"")
            );
        }

    }
    @GetMapping("/file/{fileName:.+}")

    public ResponseEntity<byte[]>readDetailFile(@PathVariable String fileName){
        try {
            byte[]bytes=services.readFileContent(fileName);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(bytes);
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }
    @GetMapping()

    public ResponseEntity<ResponseObject>getUpload(){
        try {
            List<String> urls=services.loadAll().map(path -> {
                String urlPath= MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,"readDetailFile",path.getFileName().toString()).build().toUri().toString();
                return urlPath;
            }).collect(Collectors.toList());
            return ResponseEntity.ok().body(new ResponseObject("ok","List file success",urls));

        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseObject("ok","List file failed",""));

        }
    }
    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<ResponseObject> deleteFile(@PathVariable String fileName){
        boolean delete= services.deleteFile(fileName);
        return (delete==true)?ResponseEntity.ok().body(new ResponseObject("ok","success",delete))
                :ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("failed","can not find file name",delete));
    }

}
