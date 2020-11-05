package com.thoughtworks.capacity.gtb.mvc.ErrorException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandle {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ResponseEntity<ErrorResult> handle(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        ErrorResult errorResult = new ErrorResult(400, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    @ExceptionHandler(UserIsExistException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ResponseEntity<ErrorResult> handle(UserIsExistException ex) {
        ErrorResult errorResult = new ErrorResult(400, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    @ExceptionHandler(WrongUsernameOrPasswordException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ResponseEntity<ErrorResult> handle(WrongUsernameOrPasswordException ex) {
        ErrorResult errorResult = new ErrorResult(404, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResult);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ResponseEntity<ErrorResult> handle(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();

        String message = "";
        for (ConstraintViolation<?> constraint : ex.getConstraintViolations()) {
            message = constraint.getMessage();
            break;
        }
        ErrorResult errorResult = new ErrorResult(404,message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResult> handle(Exception ex) {
        ErrorResult errorResult = new ErrorResult(500,"服务端错误");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResult);
    }
}
