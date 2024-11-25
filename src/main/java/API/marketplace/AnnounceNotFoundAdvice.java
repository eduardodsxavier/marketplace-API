package API.marketplace;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class AnnounceNotFoundAdvice {
    @ExceptionHandler(AnnounceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String AnnounceNotFoundHandler(AnnounceNotFoundException ex) {
        return ex.getMessage();
    }
}
