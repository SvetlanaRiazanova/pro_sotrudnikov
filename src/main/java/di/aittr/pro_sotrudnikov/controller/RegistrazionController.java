package di.aittr.pro_sotrudnikov.controller;

import di.aittr.pro_sotrudnikov.domen.dto.SotrudnikDto;
import di.aittr.pro_sotrudnikov.response.Response;
import di.aittr.pro_sotrudnikov.servise.interfaces.SotrudnikServise;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@Tag(name = "Registrazion controller", description = "Контроллер для регистрации сотрудников в базе данных")
public class RegistrazionController {

    private final SotrudnikServise servise;

    public RegistrazionController(SotrudnikServise servise) {
        this.servise = servise;
    }

    @PostMapping
    @Operation(
            summary = "Регистрация сотрудника",
            description = "Процесс регистрации сотрудника в базе данных " +
                    "и отправка ему сообщения о регистрации на адрес его электронной почты")
    public Response register(@RequestBody
                             @io.swagger.v3.oas.annotations.parameters.
                                     RequestBody(description = "Дто сотрудника с заполненным именем, логином и паролем")
                             SotrudnikDto sotrudnik) {
        servise.register(sotrudnik);
        return new Response("Регистрация успешна. Проверьте почту для подтверждения регистрации.");
    }

    @GetMapping("/{code}")
    @Operation(
            summary = "Подтверждение регистрации",
            description = "Подтверждение регистрации сотрудника в базе данных"
    )
    public Response confirmation(@PathVariable
                                 @Parameter(description = "Уникальный код сотрудника")
                                 String code) {
        servise.confirmation(code);

        return new Response("Регистрация подтверждена");


    }
}
