package com.imyc.SBAP.Exception;

import com.imyc.SBAP.Base.viewobject.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ValidationExceptionHandler {
    /**
     * from any validation error
     * @param ex
     * @return Json
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleDTOValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();

        fieldErrors.forEach(error -> {
                    if (errors.containsKey(error.getField())) {
                        errors.put(error.getField(), String.format("%s, %s", errors.get(error.getField()), error.getDefaultMessage()));
                    } else {
                        errors.put("error_message", error.getDefaultMessage());
                    }
                }
        );

        globalErrors.forEach(error -> {
                    errors.put("error_message", error.getDefaultMessage());
                }
        );

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(errors);

        return new ResponseEntity<>(errorResponse.toJson(), HttpStatus.BAD_REQUEST);
    }
}
