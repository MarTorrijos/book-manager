    package org.example.bookmanager.advice;

    import org.example.bookmanager.exceptions.AuthorNotFound;
    import org.example.bookmanager.exceptions.BookNotAddedException;
    import org.example.bookmanager.exceptions.BookNotFoundException;
    import org.example.bookmanager.exceptions.InvalidBookException;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.ControllerAdvice;
    import org.springframework.web.bind.annotation.ExceptionHandler;

    import java.time.LocalDateTime;
    import java.util.HashMap;
    import java.util.Map;

    @ControllerAdvice
    public class ExceptionAdvice {

        private ResponseEntity<Object> createErrorResponse(Exception e, HttpStatus status, String errorType) {
            Map<String, Object> body = new HashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("error", errorType);
            body.put("message", e.getMessage());

            return ResponseEntity.status(status).body(body);
        }

        @ExceptionHandler(AuthorNotFound.class)
        public ResponseEntity<Object> handleAuthorNotFound(AuthorNotFound e) {
            return createErrorResponse(e, HttpStatus.NOT_FOUND, "Author Not Found");
        }

        @ExceptionHandler(BookNotFoundException.class)
        public ResponseEntity<Object> handleBookNotFound(BookNotFoundException e) {
            return createErrorResponse(e, HttpStatus.NOT_FOUND, "Book Not Found");
        }

        @ExceptionHandler(BookNotAddedException.class)
        public ResponseEntity<Object> handleBookNotAdded(BookNotAddedException e) {
            return createErrorResponse(e, HttpStatus.BAD_REQUEST, "Book Not Added");
        }

        @ExceptionHandler(InvalidBookException.class)
        public ResponseEntity<Object> handleInvalidBook(InvalidBookException e) {
            return createErrorResponse(e, HttpStatus.BAD_REQUEST, "Invalid Book");
        }

    }