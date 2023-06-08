package pl.kurs.shape_api.exceptionhandling.constraint;

import pl.kurs.shape_api.exceptionhandling.ExceptionResponse;

public interface ConstraintErrorHandler {

    ExceptionResponse mapToResponse();
    String getConstraintName();
}
