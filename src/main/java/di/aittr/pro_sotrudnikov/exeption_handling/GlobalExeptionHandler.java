package di.aittr.pro_sotrudnikov.exeption_handling;

import di.aittr.pro_sotrudnikov.exeption_handling.handling.exeptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExeptionHandler {

    @ExceptionHandler(SotrudnikNeNaidenExeption.class)
    public ResponseEntity<Response> handlerExeption(SotrudnikNeNaidenExeption e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SotrudnikValidacionExeption.class)
    public ResponseEntity<Response> handlerExeption(SotrudnikValidacionExeption e) {
        String[] partsResponse = e.getMessage().split("'");
        Response response = new Response(partsResponse[1]);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ProektValidacionExeption.class)
    public ResponseEntity<Response> handlerExeption(ProektValidacionExeption e) {
        String[] partsResponse = e.getMessage().split("'");
        Response response = new Response(partsResponse[1]);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ZadacaValidacionExeption.class)
    public ResponseEntity<Response> handlerExeption(ZadacaValidacionExeption e) {
        String[] partsResponse = e.getMessage().split("'");
        Response response = new Response(partsResponse[1]);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IstekSrokDeistviyaCoda.class)
    public ResponseEntity<Response> handlerExeption(IstekSrokDeistviyaCoda e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}


