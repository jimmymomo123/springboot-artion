package com.jimmychiu.artion.controller;

import com.jimmychiu.artion.dto.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @PostMapping
    public Result<String> upload(MultipartFile file) throws IOException {
        //將文件內容儲存到本地磁盤上
        String originalFileName = file.getOriginalFilename();
        //保證文件的名字是唯一的,從而防止文件覆蓋
        String fileName = UUID.randomUUID().toString() + originalFileName.substring(originalFileName.lastIndexOf("."));
        file.transferTo(new File("" + fileName));
        return Result.success("Url訪問地址...");
    }
}
