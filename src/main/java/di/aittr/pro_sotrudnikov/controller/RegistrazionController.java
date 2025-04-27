package di.aittr.pro_sotrudnikov.controller;

import di.aittr.pro_sotrudnikov.domen.entity.Sotrudnik;
import di.aittr.pro_sotrudnikov.response.Response;
import di.aittr.pro_sotrudnikov.servise.interfaces.SotrudnikServise;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegistrazionController {

    private final SotrudnikServise servise;

    public RegistrazionController(SotrudnikServise servise) {
        this.servise = servise;
    }

    public Response register(@RequestBody Sotrudnik sotrudnik){
        servise.register(sotrudnik);
        return new Response("Регистрация успешна. Проверьте почту для подтверждения регистрации.");
    }
}
