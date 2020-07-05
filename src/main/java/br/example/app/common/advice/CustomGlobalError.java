package br.example.app.common.advice;

import br.example.app.common.ErrorMsg;
import br.example.app.customer.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class CustomGlobalError {

   private final static Logger LOG = LoggerFactory.getLogger(CustomGlobalError.class);

    @ExceptionHandler(value = Exception.class)
    protected  ResponseEntity<Object> handleRequestFailedException(final Exception ex) {
       return formatMensage(HttpStatus.BAD_REQUEST,"Error:", ex );
    }

    @ExceptionHandler(value = CustomException.class)
    protected  ResponseEntity<Object> customException(final Exception ex) {
        return formatMensage(HttpStatus.BAD_REQUEST,"Customer Fail", ex );
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    protected  ResponseEntity<Object> entityNotFoundException(final Exception ex) {
       return formatMensage(HttpStatus.BAD_REQUEST,"Not found Object", ex );
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected  ResponseEntity<Object> entityIllegalArgumentException(final Exception ex) {
       return formatMensage(HttpStatus.NOT_FOUND,"Contract Invalid", ex );
    }

    private ResponseEntity<Object> formatMensage(HttpStatus httpStatus, String msg, Exception ex){
       LOG.info(ex.getMessage());
       return ResponseEntity.status(httpStatus).body(new ErrorMsg(httpStatus.name(),msg, ex.getMessage(), httpStatus.value()));
    }

}
