package di.aittr.pro_sotrudnikov.controller;

import di.aittr.pro_sotrudnikov.domen.dto.SotrudnikDto;
import di.aittr.pro_sotrudnikov.response.Response;
import di.aittr.pro_sotrudnikov.servise.interfaces.SotrudnikServise;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegistrazionController {

    private final SotrudnikServise servise;

    public RegistrazionController(SotrudnikServise servise) {
        this.servise = servise;
    }

    @PostMapping
    public Response register(@RequestBody SotrudnikDto sotrudnik){
        servise.register(sotrudnik);
        return new Response("Регистрация успешна. Проверьте почту для подтверждения регистрации.");
    }

    @GetMapping("/{code}")
    public Response confirmation(@PathVariable String code){
        servise.confirmation(code);

        return new Response("Регистрация подтверждена");


    }
}
