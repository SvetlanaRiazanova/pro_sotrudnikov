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
    @ExceptionHandler(IstekSrokDeistviyaCodaExeption.class)
    public ResponseEntity<Response> handlerExeption(IstekSrokDeistviyaCodaExeption e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OschibkaRegistraziiExeption.class)
    public ResponseEntity<Response> handlerExeption(OschibkaRegistraziiExeption e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProektNeNaidenExeption.class)
    public ResponseEntity<Response> handlerExeption(ProektNeNaidenExeption e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ZadacaNeNaidenExeption.class)
    public ResponseEntity<Response> handlerExeption(ZadacaNeNaidenExeption e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OschibkaPodtverzcdeniyaCodaExeption.class)
    public ResponseEntity<Response> handlerExeption(OschibkaPodtverzcdeniyaCodaExeption e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OschibkaOtpravkiSoobshcheniyaExeption.class)
    public ResponseEntity<Response> handlerExeption(OschibkaOtpravkiSoobshcheniyaExeption e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RoleNeNaidenExeption.class)
    public ResponseEntity<Response> handlerExeption(RoleNeNaidenExeption e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OschibkaAvtorisaziiExeption.class)
    public ResponseEntity<Response> handlerExeption(OschibkaAvtorisaziiExeption e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}


