package UnityDwell.com.UnityDwell.error;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

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
}
