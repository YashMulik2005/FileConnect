package com.CCL.FileConnect.serivce.impl;

import com.CCL.FileConnect.dto.FileResponseDTO;
import com.CCL.FileConnect.dto.MailRequestDto;
import com.CCL.FileConnect.exception.MailServiceException;
import com.CCL.FileConnect.exception.NotFoundException;
import com.CCL.FileConnect.exception.ParameterMissingException;
import com.CCL.FileConnect.model.File;
import com.CCL.FileConnect.repository.FileRepository;
import com.CCL.FileConnect.serivce.FileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private S3Service s3Service;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MailService mailService;

    @Override
    public FileResponseDTO uploadFile(MultipartFile file) {
        if(file == null  || file.isEmpty()){
            throw new ParameterMissingException("Required Parameters are missing.");
        }

        String fileKey = s3Service.upload(file);
        System.out.println("file uploaded");
        String url;
        do{
            url = RandomURLGenerator.generateURL(7);
        }while(fileRepository.findByUrl(url).isPresent());

        File file1 = new File();
        file1.setFile(fileKey);
        file1.setFileName(file.getOriginalFilename());
        file1.setContentType(file.getContentType());
        file1.setSize(file.getSize());
        file1.setUrl(url);

        File savedFile = fileRepository.save(file1);

        System.out.println(savedFile);

        FileResponseDTO res = modelMapper.map(savedFile, FileResponseDTO.class);
        res.setFile(s3Service.getUrl(res.getFile()));
        return res;
    }

    @Override
    public FileResponseDTO getFileByUrl(String url) {
        File file = fileRepository.findByUrl(url)
                .orElseThrow(()-> new NotFoundException("File not found."));

        FileResponseDTO res = modelMapper.map(file, FileResponseDTO.class);
        res.setFile(s3Service.getUrl(res.getFile()));
        return res;
    }

    @Override
    public void sendUrlByMail(MailRequestDto mailRequestDto) {
        try {
            File file = fileRepository.findByUrl(mailRequestDto.getUrl())
                    .orElseThrow(() -> new NotFoundException("Url not found."));

            String subject = "File Shared With You";

            String body = """
<h2>A file has been shared with you</h2>
<p><b>File Name:</b> %s</p>
<p><b>File Type:</b> %s</p>
<p><b>File Size:</b> %d bytes</p>
<p><a href="%s">Access File</a></p>
""".formatted(
                    file.getFileName(),
                    file.getContentType(),
                    file.getSize(),
                    "http://localhost:5173/file/" + file.getUrl()
            );

            mailService.sendmail(mailRequestDto.getMail(), subject, body);
        }catch(Exception ex){
            throw new MailServiceException(ex.getMessage());
        }
    }
}
