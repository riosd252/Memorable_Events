package DavidRios.Memorable_Events.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsDTO handleBadRequest(BadRequestException ex) {
        if (ex.getErrorList() != null) {
            List<String> errorList = ex.getErrorList().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();

            return new ErrorsListDTO(ex.getMessage(), LocalDateTime.now(), errorList);
        } else {
            return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
        }

    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorsDTO handleUnauthorized(UnauthorizedException ex) {
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsDTO handleNotFound(NotFoundException ex) {
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public ErrorsDTO handleGenericErrors(Exception ex) {
        ex.printStackTrace();
        return new ErrorsDTO("Our bad, sorry! Here's a cup of tea for you to taste while we take care of this 🍵", LocalDateTime.now());
    }
}
