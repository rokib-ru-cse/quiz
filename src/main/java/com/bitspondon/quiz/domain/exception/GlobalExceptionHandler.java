package com.bitspondon.quiz.domain.exception;


import com.bitspondon.quiz.domain.ReturnReponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(value = Exception.class)
//    public ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
//        ReturnReponse<Object> response = new ReturnReponse<Object>();
//        response.setSucceeded(false);
//        response.setValue(null);
//        if (ex instanceof CustomException) {
//            response.setMessage(ex.getMessage());
//        } else if (ex instanceof IOException) {
//            response.setMessage("IOException. ErrorCode:");
//        } else if (ex instanceof SQLException) {
//            response.setMessage("SQL exception. ErrorCode:");
//        } else if (ex instanceof NumberFormatException) {
//            response.setMessage("Number format exception. ErrorCode:");
//        }
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ReturnReponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ReturnReponse<Object> response = ReturnReponse.<Object>builder()
                .message(ex.getBindingResult().getFieldError().getField() + " "
                        + ex.getBindingResult().getFieldError().getDefaultMessage())
                .succeeded(false).build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}