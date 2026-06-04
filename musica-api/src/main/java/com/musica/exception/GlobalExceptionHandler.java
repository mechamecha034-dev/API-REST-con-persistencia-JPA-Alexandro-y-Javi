package com.musica.exception;

import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> manejarValidacion(MethodArgumentNotValidException exception) {
        List<String> mensajes = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        return construirRespuesta(HttpStatus.BAD_REQUEST, "Datos no validos", mensajes);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> manejarJsonIncorrecto(HttpMessageNotReadableException exception) {
        return construirRespuesta(
                HttpStatus.BAD_REQUEST,
                "JSON incorrecto",
                List.of("Revisa que el cuerpo de la peticion tenga formato JSON valido"));
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<ApiError> manejarOrdenacionIncorrecta(PropertyReferenceException exception) {
        return construirRespuesta(
                HttpStatus.BAD_REQUEST,
                "Parametro de ordenacion incorrecto",
                List.of("El campo sortBy no existe en la entidad indicada"));
    }

    private ResponseEntity<ApiError> construirRespuesta(HttpStatus status, String error, List<String> mensajes) {
        ApiError apiError = new ApiError(LocalDateTime.now(), status.value(), error, mensajes);
        return ResponseEntity.status(status).body(apiError);
    }
}
