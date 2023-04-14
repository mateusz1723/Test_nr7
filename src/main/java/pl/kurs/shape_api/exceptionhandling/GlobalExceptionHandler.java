package pl.kurs.shape_api.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleRuntimeException(IllegalArgumentException ex) {
        ExceptionResponse response = new ExceptionResponse(
                List.of(ex.getMessage()),
                "BAD_REQUEST",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClassCastException.class)
    public ResponseEntity<ExceptionResponse> handleRuntimeException(ClassCastException ex) {
        ExceptionResponse response = new ExceptionResponse(
                List.of(ex.getMessage()),
                "BAD_REQUEST",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errorsMessages = ex.getFieldErrors()
                .stream()
                .map(fe -> "Field: " + fe.getField() + " /  rejected value: '" + fe.getRejectedValue() + "' / message: " + fe.getDefaultMessage())
                .collect(Collectors.toList());


        ExceptionResponse response = new ExceptionResponse(
                errorsMessages,
                "BAD_REQUEST",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
