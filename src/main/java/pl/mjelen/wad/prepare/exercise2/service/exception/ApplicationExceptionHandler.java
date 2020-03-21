package pl.mjelen.wad.prepare.exercise2.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionInfo> handle(ApplicationException ex) {
        ExceptionInfo info = ExceptionInfo.builder()
                .status(HttpStatus.NOT_ACCEPTABLE.value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(info, HttpStatus.NOT_ACCEPTABLE);
    }
}
