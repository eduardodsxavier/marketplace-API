package API.marketplace.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AnnounceNotFoundAdvice {
    @ExceptionHandler(AnnounceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String AnnounceNotFoundHandler(AnnounceNotFoundException ex) {
        return ex.getMessage();
    }
}
