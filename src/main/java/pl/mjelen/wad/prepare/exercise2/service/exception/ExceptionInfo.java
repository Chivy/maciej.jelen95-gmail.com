package pl.mjelen.wad.prepare.exercise2.service.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ExceptionInfo {
    private final int status;
    private final String message;
    private final LocalDateTime timestamp;
}
