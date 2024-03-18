package lt.techin.cinema.exceptions.handler;

import lt.techin.cinema.exceptions.TicketAlreadyPurchasedException;
import lt.techin.cinema.exceptions.WrongRowOrColumnNumberException;
import lt.techin.cinema.exceptions.WrongTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TicketAlreadyPurchasedException.class)
    public ResponseEntity<ErrorDetails> exceptionTicketAlreadyPurchasedHandler(TicketAlreadyPurchasedException ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongRowOrColumnNumberException.class)
    public ResponseEntity<ErrorDetails> exceptionWrongRowOrColumnNumberHandler(WrongRowOrColumnNumberException ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongTokenException.class)
    public ResponseEntity<ErrorDetails> exceptionWrongTokenHandler(WrongTokenException ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
