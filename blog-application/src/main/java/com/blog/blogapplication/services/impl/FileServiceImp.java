package com.blog.blogapplication.services.impl;

import java.io.File;
// import java.io.File;
// import java.io.File; 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.blogapplication.services.FileService;

@Service
public class FileServiceImp implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        // String date = new Date().getTime().toString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String date = formatter.format(new Date());
        String fname = file.getOriginalFilename();
        long size = file.getSize();
        System.out.println("size " + size);
        System.out.println("fname " + fname);
        String fname1 = fname + "_" + date;
        System.out.println("fname1 " + fname1);

        String filePath = path + fname1;
        System.out.println("filePath " + filePath);

        File file1 = new File(path);
        if (!file1.exists()) {
            file1.mkdir();
        }
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return fname;

    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream inputStream = new FileInputStream(fullPath);

        return inputStream;
    }

}
