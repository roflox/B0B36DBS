package cz.pazdera.school.dbs.DBS_Hotel.controller.api.rest.v1.utils;

import cz.pazdera.school.dbs.DBS_Hotel.controller.api.rest.v1.utils.exception.CustomValidationException;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.UnexpectedTypeException;


@ControllerAdvice
public class RestExceptionHandler {

    private static final Logger console = LoggerFactory.getLogger(RestExceptionHandler.class);

    private static void logException(RuntimeException ex) {
        console.error("Exception caught:", ex);
    }

    private static ErrorInfo errorInfo(HttpServletRequest request, Throwable e) {
        return new ErrorInfo(e.getMessage(), request.getRequestURI());
    }

    private static ErrorInfo errorInfo(HttpServletRequest request, String message){
        return new ErrorInfo(message,request.getRequestURI());
    }


    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<ErrorInfo> persistenceException(HttpServletRequest request, PersistenceException e) {
        logException(e);
        return new ResponseEntity<>(errorInfo(request, e.getCause()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorInfo> resourceNotFound(HttpServletRequest request, NotFoundException e) {
        return new ResponseEntity<>(errorInfo(request, e), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorInfo> nullPointer(HttpServletRequest request, NullPointerException e) {
        logException(e);
        return new ResponseEntity<>(errorInfo(request, e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorInfo> messageNotReadable(HttpServletRequest request, HttpMessageNotReadableException e){
        return new ResponseEntity<>(errorInfo(request,e),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorInfo> accessDenied(HttpServletRequest request, AccessDeniedException e){
//        e.printStackTrace();
        return new ResponseEntity<>(errorInfo(request,"Access denied"),HttpStatus.FORBIDDEN);
//        return null;
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ErrorInfo> entityExists(HttpServletRequest request, EntityExistsException e){
        return new ResponseEntity<>(errorInfo(request,e),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInfo> validationError(HttpServletRequest request, MethodArgumentNotValidException e){
        return new ResponseEntity<>(errorInfo(request,new CustomValidationException(e)),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<ErrorInfo> unexpectedType(HttpServletRequest request, UnexpectedTypeException e){
        e.printStackTrace();
        return new ResponseEntity<>(errorInfo(request,"unexpectedType"),HttpStatus.BAD_REQUEST);
    }

}


class ErrorInfo {

    private String message;

    private String requestUri;

    public ErrorInfo() {
    }

    public ErrorInfo(String message, String requestUri) {
        this.message = message;
        this.requestUri = requestUri;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    @Override
    public String toString() {
        return "ErrorInfo{" + requestUri + ", message = " + message + "}";
    }
}

