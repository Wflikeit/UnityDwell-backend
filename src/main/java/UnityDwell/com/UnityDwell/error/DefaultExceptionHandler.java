package UnityDwell.com.UnityDwell.error;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class DefaultExceptionHandler {
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public ErrorResponse handleDuplicateIdException(HttpServletResponse response,
                                                    DuplicateKeyException ex) {
        response.setStatus(HttpServletResponse.SC_CONFLICT);

        log.error("DuplicateKeyException", ex);
        return ErrorResponse.builder()
                .message(ex.getMessage())
                .additionalData(new HashMap<>())
                .build();

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ErrorResponse handleResourceNotFoundException(HttpServletResponse response,
                                                         ResourceNotFoundException ex) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);

        log.error("ResourceNotFoundException", ex);
        return ErrorResponse.builder()
                .message(ex.getMessage())
                .additionalData(new HashMap<>())
                .build();

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ErrorResponse handleInvalidUUID(HttpServletResponse response,
                                           MethodArgumentTypeMismatchException ex) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        String message = ex.getMessage();
        log.error(message, ex);
        return ErrorResponse.builder()
                .message(message)
                .additionalData(new HashMap<>())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorResponse handleValidationException(HttpServletResponse response,
                                                   MethodArgumentNotValidException ex) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        Map<String, String> additionalData = new HashMap<>();
        for (FieldError fieldError : ex.getFieldErrors()) {
            additionalData.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        log.error("MethodArgumentNotValidException", ex);

        return ErrorResponse.builder()
                .message("Method arguments not valid!")
                .additionalData(additionalData)
                .build();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ErrorResponse handleDataIntegrityViolationException(HttpServletResponse response,
                                                               DataIntegrityViolationException ex) {
        response.setStatus(HttpServletResponse.SC_CONFLICT);

        Map<String, String> additionalData = new HashMap<>();

        String message = ex.getMessage();
        log.error(message, ex);

        return ErrorResponse.builder()
                .message("Cannot delete this record because it's used somewhere else")
                .additionalData(additionalData)
                .build();
    }
}
