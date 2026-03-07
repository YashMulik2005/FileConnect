package com.CCL.FileConnect.exception;


import com.CCL.FileConnect.dto.ErrorResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import software.amazon.awssdk.services.s3.model.S3Exception;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(S3UploadException.class)
    public ResponseEntity<?> handleS3UploadException(S3Exception ex){
        return ErrorResponseHandler.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage(),false);
    }

    @ExceptionHandler(ParameterMissingException.class)
    public ResponseEntity<?>HandleParameterMissingException(ParameterMissingException ex){
        return ErrorResponseHandler.errorResponse(HttpStatus.BAD_REQUEST,ex.getMessage(),false);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?>HandleNotFoundException(NotFoundException ex){
        return ErrorResponseHandler.errorResponse(HttpStatus.NOT_FOUND,ex.getMessage(),false);
    }

    @ExceptionHandler(MailServiceException.class)
    public ResponseEntity<?>HandleMailServiceException(MailServiceException ex){
        return ErrorResponseHandler.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage(),false);
    }
}
