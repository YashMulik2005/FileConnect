package com.CCL.FileConnect.serivce;

import com.CCL.FileConnect.dto.FileResponseDTO;
import com.CCL.FileConnect.dto.MailRequestDto;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    FileResponseDTO uploadFile(MultipartFile file);
    FileResponseDTO getFileByUrl(String url);
    void sendUrlByMail(MailRequestDto mailRequestDto);
}
