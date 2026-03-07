package com.CCL.FileConnect.controller;

import com.CCL.FileConnect.dto.MailRequestDto;
import com.CCL.FileConnect.dto.SuccessResponseHandler;
import com.CCL.FileConnect.serivce.impl.FileServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class FileController {

    @Autowired
    private FileServiceImpl fileServiceImpl;

    @PostMapping("/upload")
    public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file){
        return SuccessResponseHandler.successResponse(HttpStatus.CREATED, "File uploaded successfully,",
                true, fileServiceImpl.uploadFile(file));
    }

    @GetMapping("/file/{url}")
    public ResponseEntity<Object> getFile(@PathVariable String url){
        return SuccessResponseHandler.successResponse(HttpStatus.OK, "Data fetched successfully",
                true, fileServiceImpl.getFileByUrl(url));
    }

    @PostMapping("/file/shareByMail")
    public ResponseEntity<Object> sendLinkByMail(@Valid @RequestBody MailRequestDto mailRequestDto){
        fileServiceImpl.sendUrlByMail(mailRequestDto);
        return SuccessResponseHandler.successResponse(HttpStatus.OK, "Mail sent successfully",
                true, null);
    }
}
