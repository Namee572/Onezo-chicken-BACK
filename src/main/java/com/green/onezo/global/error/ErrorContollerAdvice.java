package com.green.onezo.global.error;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorContollerAdvice extends ResponseEntityExceptionHandler {

    // 프론트에서 보내는 데이터가 잘못된 경우
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(HttpStatus.BAD_REQUEST.toString())
                .errorMessage(String.join("\n", errors))
                .errorDateTime(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // 다양한 예외를 추가적으로 처리
    @ExceptionHandler({RuntimeException.class, ResponseStatusException.class})
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .errorMessage(ex.getMessage())
                .errorDateTime(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}

//        java.util.List<FieldError> list = ex.getFieldErrors();
//        String errorMessage = "";
//
//        for (FieldError error : list) {
//            errorMessage += error.getDefaultMessage() + "\n";
//        }
//
//        ErrorResponse errorResponse = ErrorResponse
//                .builder()
//                .errorCode(HttpStatus.BAD_REQUEST.toString())
//                .errorMessage( errorMessage )
//                .errorDateTime(LocalDateTime.now())
//                .build();
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
//    }
//}
