package com.poec.sortie_facile_backend.exceptions;

import com.poec.sortie_facile_backend.exceptions.model.ErrorResponse;
import com.poec.sortie_facile_backend.util.Helper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * to handle the case when a NotFoundException is thrown
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException exception, HttpServletRequest request) {
        return handleException(exception, HttpStatus.NOT_FOUND, request, null);
    }

    /**
     * to handle the case when the user is not found.
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException exception, HttpServletRequest request) {
        return handleException(exception, HttpStatus.NOT_FOUND, request, null);
    }

    /**
     * to handle the case when the requested entity is not found.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException exception, HttpServletRequest request) {
        return handleException(exception, HttpStatus.NOT_FOUND, request, null);
    }

    /**
     * to handle the case when the provided username already exists in the database.
     */
    @ExceptionHandler(UsernameAlreadyTakenException.class)
    public ResponseEntity<ErrorResponse> handleUsernameAlreadyTakenException(UsernameAlreadyTakenException exception, HttpServletRequest request) {
        return handleException(exception, HttpStatus.NOT_FOUND, request, null);
    }

    /**
     * to handle the case when the provided username or password are incorrect.
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException exception, HttpServletRequest request) {
        return handleException(exception, HttpStatus.NOT_FOUND, request, null);
    }

    /**
     * to handle the case when the user does not have the necessary roles
     * to access the requested resource.
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException exception, HttpServletRequest request) {
        return handleException(exception, HttpStatus.NOT_FOUND, request, null);
    }

    /**
     * to handle the case when the incoming request does not match the expected type.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception, HttpServletRequest request) {
        return handleException(
                exception,
                HttpStatus.BAD_REQUEST,
                request,
                String.format(
                        "The parameter '%s' of value '%s' could not be converted to type '%s'",
                        exception.getName(),
                        exception.getValue(),
                        Objects.requireNonNull(exception.getRequiredType()).getSimpleName()
                )
        );
    }

    /**
     * to handle the case when trying to access an object that does not exist
     * missing or malformed
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception, HttpServletRequest request) {
        String message = "";

        // Check if the error is for a missing request body
        if (exception.getMessage() != null && exception.getMessage().contains("Required request body is missing")) {
            message = "Request body is missing or malformed";
        } else {
            message = "Malformed JSON request: " + exception.getMessage();
        }

        return handleException(exception, HttpStatus.BAD_REQUEST, request, message);
    }

    /**
     * to handle the case when invalid or inappropriate argument.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();

        // handle field-specific errors
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        // handle global errors
        exception.getBindingResult().getGlobalErrors().forEach((ObjectError error) -> {
            if (Objects.equals(error.getCode(), "ValidAgeRange")) {
                errors.put("age", error.getDefaultMessage());
            }
        });

        errors.put("validate", "false");

        return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Others
     */
    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleException(Exception exception) {
        return handleException(exception, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> handleException(Exception exception, HttpStatus status, HttpServletRequest request, String message) {
        exception.printStackTrace();

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setMessage(message != null ? message : exception.getMessage());
        errorResponse.setStatus(status.value());
        errorResponse.setExceptionName(exception.getClass().getSimpleName());
        errorResponse.setDate(Helper.simpleDateFormat());
        errorResponse.setPath(request.getRequestURI());

        return new ResponseEntity<>(errorResponse, status);
    }

    private ResponseEntity<Map<String, String>> handleException(Exception ex, HttpStatus status) {
        Map<String, String> response = new HashMap<>();
        String errorMessage = ex.getMessage();
        response.put("Error", errorMessage);

        return ResponseEntity.status(status)
                .body(response);
    }
}

