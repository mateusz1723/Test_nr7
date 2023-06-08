package pl.kurs.shape_api.exceptionhandling;

import org.hibernate.StaleObjectStateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.kurs.shape_api.exceptionhandling.constraint.ConstraintErrorHandler;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Map<String, ConstraintErrorHandler> handlers;

    public GlobalExceptionHandler(Set<ConstraintErrorHandler> handlers) {
        this.handlers = handlers.stream().collect(Collectors.toMap(ConstraintErrorHandler::getConstraintName, Function.identity()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ExceptionResponse response = new ExceptionResponse(
                List.of(ex.getMessage()),
                "BAD_REQUEST",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClassCastException.class)
    public ResponseEntity<ExceptionResponse> handleClassCastException(ClassCastException ex) {
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

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<ExceptionResponse> handleObjectOptimisticLockingFailureException(ObjectOptimisticLockingFailureException ex) {
        ExceptionResponse response = new ExceptionResponse(
                List.of(Objects.requireNonNull(ex.getMessage())),
                "BAD_REQUEST",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        String constraintName = ex.getConstraintName().substring(8, ex.getConstraintName().indexOf(' ') - 8);
        return new ResponseEntity<>(handlers.get(constraintName).mapToResponse(), HttpStatus.BAD_REQUEST);
    }

}
