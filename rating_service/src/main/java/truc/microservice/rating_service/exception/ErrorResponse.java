package truc.microservice.rating_service.exception;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ErrorResponse {
    private Date time;
    private String message;
    private String details;
    private HttpStatus status;

    public ErrorResponse(Date time, String message, String details, HttpStatus status)
    {
        this.time = time;
        this.message = message;
        this.details = details;
        this.status =status;
    }

    public HttpStatus getStatus()
    {
        return status;
    }
}
