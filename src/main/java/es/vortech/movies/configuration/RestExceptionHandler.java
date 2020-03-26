package es.vortech.movies.configuration;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

@Configuration
@ControllerAdvice
public class RestExceptionHandler {

    private static final Logger LOCAL_LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity handleMissingParameterException(IllegalArgumentException e) {
        LOCAL_LOGGER.warn(e.getMessage());
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler({InvalidArgumentException.class})
    public ResponseEntity handleInvalidArgumentException(InvalidArgumentException e) {
        LOCAL_LOGGER.warn(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }

    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity handleConstraintViolation(RuntimeException e) {
        ResponseEntity response;
        if (e instanceof ConstraintViolationException) {
            response = ResponseEntity.badRequest().build();
            return response;
        } else if (e instanceof ValidationException) {
            if (e.getCause() instanceof InvalidArgumentException) {
                response = handleInvalidArgumentException((InvalidArgumentException) e.getCause());
            } else {
                response = ResponseEntity.badRequest().build();
            }
        } else {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        LOCAL_LOGGER.error(e.getMessage(), e);
        return response;
    }
}
