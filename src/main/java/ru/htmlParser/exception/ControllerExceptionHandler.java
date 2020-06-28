package ru.htmlParser.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Класс является обработчиком ошибок. При той или иной ошибке возвращается соотвествующее сообщение.
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Внутренняя ошибка сервиса, проверьте корректность входных данных";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(INTERNAL_SERVER_ERROR_MESSAGE);
    }

}
