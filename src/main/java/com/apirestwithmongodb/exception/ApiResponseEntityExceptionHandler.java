package com.apirestwithmongodb.exception;

import com.apirestwithmongodb.constant.MessageEnum;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class ApiResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ Exception.class })
    public final ResponseEntity<Object> handleInternalServerErrorException (Exception ex, WebRequest req) {
        HashMap<String, String> res = new HashMap<>();
        res.put("error",
                ex.getMessage() != null && !ex.getMessage().trim().isEmpty() ?
                        ex.getMessage() : MessageEnum.INTERNAL_SERVER_ERROR.getMessage());
        return new ResponseEntity<Object>(res, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ ApplicationException.class })
    public final ResponseEntity<Object> handleApplicationErrorException(Exception ex, WebRequest req) {
        HashMap<String, String> res = new HashMap<>();
        res.put("error",
                ex.getMessage() != null && !ex.getMessage().trim().isEmpty() ?
                        ex.getMessage() : MessageEnum.METHOD_FAILURE.getMessage());
        return new ResponseEntity<Object>(res, new HttpHeaders(), HttpStatus.METHOD_FAILURE);
    }

    @ExceptionHandler({ UnauthorizedException.class })
    public final ResponseEntity<Object> handleUnauthorizedErrorException(WebRequest req) {
        HashMap<String, String> res = new HashMap<>();
        res.put("error", MessageEnum.UNAUTHORIZED.getMessage());
        return new ResponseEntity<Object>(res, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ ForbiddenException.class })
    public final ResponseEntity<Object> handleForbiddenErrorException(WebRequest req) {
        HashMap<String, String> res = new HashMap<>();
        res.put("error", MessageEnum.FORBIDDEN.getMessage());
        return new ResponseEntity<Object>(res, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ PreConditionFailedException.class })
    public final ResponseEntity<Object> handlePreConditionFailedErrorException(WebRequest req) {
        HashMap<String, String> res = new HashMap<>();
        res.put("error", MessageEnum.PARAMETER_EMPTY_OR_NULL.getMessage());
        return new ResponseEntity<Object>(res, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

}
