package com.project.planner.ExceptionReturn;

import com.project.planner.dto.DefaultError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice

public class ApplicationExceptionHandler {

    /* @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        log.info("Caiu");*/

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<DefaultError> handleIllegalArgumentException(IllegalArgumentException e) {
            log.error("Erro de validação: {}", e.getMessage());

            DefaultError error = new DefaultError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<DefaultError> handleException(Exception e) {
            log.error("Erro interno ao processar a requisição", e);

            DefaultError error = new DefaultError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro interno ao processar a requisição.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }

      /*  if (IllegalArgumentException.class.isAssignableFrom(e.getClass())) {
            return (ResponseEntity) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
         else if (e instanceof IllegalArgumentException) {
            DefaultError error = new DefaultError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } else if (e instanceof NullPointerException) {
            DefaultError error = new DefaultError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro interno ao processar a requisição.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        } else {
            DefaultError error = new DefaultError(HttpStatus.BAD_REQUEST.value(), "Erro ao processar sua requisição.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
*/

}

