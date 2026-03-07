package com.CCL.FileConnect.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class FileResponseDTO {
    private Long id;
    private String url;
    private String file;
    private String contentType;
    private String fileName;
    private Long size;

    public FileResponseDTO() {
    }

    public FileResponseDTO(Long id, String url, String file, String contentType, String fileName, Long size) {
        this.id = id;
        this.url = url;
        this.file = file;
        this.contentType = contentType;
        this.fileName = fileName;
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
