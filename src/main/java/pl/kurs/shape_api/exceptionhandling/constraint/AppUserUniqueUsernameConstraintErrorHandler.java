package pl.kurs.shape_api.exceptionhandling.constraint;

import org.springframework.stereotype.Service;
import pl.kurs.shape_api.exceptionhandling.ExceptionResponse;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppUserUniqueUsernameConstraintErrorHandler implements ConstraintErrorHandler{


    @Override
    public ExceptionResponse mapToResponse() {
        return new ExceptionResponse(List.of("USERNAME_NOT_UNIQUE"), "BAD_REQUEST", LocalDateTime.now());
    }

    @Override
    public String getConstraintName() {
        return "UC_APP_USER_USERNAME";
    }
}
