package com.ram.renew_service.api.renew_service.api.common.exception;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApplicationExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<?> handleApplicationException(final ApplicationException exception,
			final HttpServletRequest request) {

		String pattern = "EEEEE MMMMM yyyy HH:mm:ss.SSSZ";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());

		String guid = UUID.randomUUID().toString();
		logger.error(String.format("Error GUID=%s; error message: %s", guid, exception.getMessage()), exception);
		exception.printStackTrace();
		ApiErrorResponse response = new ApiErrorResponse(guid, exception.getErrorCode(), exception.getMessage(),
				exception.getHttpStatus().value(), exception.getHttpStatus().name(), request.getRequestURI(),
				request.getMethod(), date);
		return new ResponseEntity<>(response, exception.getHttpStatus());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidExceptionHandlerMethod(
            final MethodArgumentNotValidException  exception, final HttpServletRequest request
    ) {
    	
    	String pattern = "EEEEE MMMMM yyyy HH:mm:ss.SSSZ";
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    	String date = simpleDateFormat.format(new Date());
    	
       String guid = UUID.randomUUID().toString();
       logger.error(
               String.format("Error GUID=%s; error message: %s", guid, exception.getMessage()), 
               exception
           );
       exception.printStackTrace();
       
       
       String validationFieldWisemessage="";
      
      for(FieldError error: exception.getBindingResult().getFieldErrors()) {
    	  
    	  validationFieldWisemessage=validationFieldWisemessage + error.getDefaultMessage()+" ,";
    	  
      }
      
      
     
       
       
       
    	ApiErrorResponse response = new ApiErrorResponse(
    			guid,
                "Validation Exception",
                validationFieldWisemessage,
                400,
                "BAD_REQUEST",
                request.getRequestURI(),
                request.getMethod(),
                date
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

	/*
	 * @ExceptionHandler(Exception.class) public ResponseEntity<?>
	 * handleUnknownException( final Exception exception, final HttpServletRequest
	 * request ) {
	 * 
	 * String guid = UUID.randomUUID().toString();
	 * 
	 * logger.error( String.format("Error GUID=%s; error message: %s", guid,
	 * exception.getMessage()), exception ); exception.printStackTrace();
	 * ApiErrorResponse response = new ApiErrorResponse( guid, "INTERNAL_ERROR",
	 * "Internal server error", exception.getHttpStatus().value(),
	 * exception.getHttpStatus().name(), request.getRequestURI(),
	 * request.getMethod(), LocalDateTime.now() ); return new
	 * ResponseEntity<>(response, ((ApplicationException)
	 * exception).getHttpStatus()); }
	 */

}
