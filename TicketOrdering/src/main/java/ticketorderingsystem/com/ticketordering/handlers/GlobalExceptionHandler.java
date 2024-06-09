package ticketorderingsystem.com.ticketordering.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<HashMap<String, String>> handleResponseStatusException(ResponseStatusException ex) {
        HashMap<String, String> errorResponse = new HashMap<>();
        errorResponse.put("status", String.valueOf(ex.getStatusCode().value()));
        errorResponse.put("error", ex.getReason());

        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }
}
