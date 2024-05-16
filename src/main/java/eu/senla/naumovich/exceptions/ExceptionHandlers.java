package eu.senla.naumovich.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(NoRecords.class)
    public ResponseEntity<String> handleNoRecordsException(NoRecords e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoMoneyOnBankAccount.class)
    public ResponseEntity<String> handleNoMoneyOnBankAccountException(NoMoneyOnBankAccount e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        String message = "Invalid data: " + e.getValue();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> handleNoHandlerFoundException(NoHandlerFoundException e) {
        String message = "Called endpoint was not found: " + e.getRequestURL();
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handelInvalidDataInsert(DataIntegrityViolationException e){
        String message = "Invalid data insert: " + e.getMessage();
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }
}

