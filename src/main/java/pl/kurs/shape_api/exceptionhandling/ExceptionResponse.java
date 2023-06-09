package pl.kurs.shape_api.exceptionhandling;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public class ExceptionResponse {

    private List<String> errorMessages;
    private String errorCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;


    public ExceptionResponse(List<String> errorMessages, String errorCode, LocalDateTime timestamp) {
        this.errorMessages = errorMessages;
        this.errorCode = errorCode;
        this.timestamp = timestamp;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "ExceptionResponse{" +
                "errorMessage='" + errorMessages + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
