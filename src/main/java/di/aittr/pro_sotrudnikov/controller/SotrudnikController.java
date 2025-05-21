package di.aittr.pro_sotrudnikov.controller;

import di.aittr.pro_sotrudnikov.domen.dto.SotrudnikDto;
import di.aittr.pro_sotrudnikov.exeption_handling.Response;
import di.aittr.pro_sotrudnikov.exeption_handling.handling.exeptions.SotrudnikNeNaidenExeption;
import di.aittr.pro_sotrudnikov.servise.interfaces.SotrudnikServise;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sotrudniki")
@Tag(name = "Sotrudnik controller", description = "Контроллер для различных операций с сотрудниками")
public class SotrudnikController {

    private final SotrudnikServise servise;

    public SotrudnikController(SotrudnikServise servise) {
        this.servise = servise;
    }


    //REST API:
    //создать сотрудника в БД
    //POST -> http://12.34.56.78:8888/sotrudniki ( в виде JSON )

    //прочитать всех сотрудников
    //GET -> http://12.34.56.78:8888/sotrudniki

    //прочитать одного сотрудника по ИД
    //GET -> http://12.34.56.78:8888/sotrudniki/3

    //обновить сотрудника по ИД (ИД отправлять в теле)
    //PUT -> http://12.34.56.78:8888/sotrudniki

    //удалить сотрудника по ИД
    //DELETE -> http://12.34.56.78:8888/sotrudniki/3

    //удалить сотрудника по имени
    //DELETE -> http://12.34.56.78:8888/sotrudniki/by-imya/Vasya

    @PostMapping
    @Operation(
            summary = "Создать сотрудника в БД",
            description = "Процесс сохранения сотрудника в базу данных"
    )
    public SotrudnikDto sozdat(@RequestBody
                               @io.swagger.v3.oas.annotations.parameters.
                                       RequestBody(description = "Дто сотрудника с заполненным именем, логином и паролем")
                               SotrudnikDto sotrudnik) {
        return servise.sozdat(sotrudnik);
    }

    @GetMapping
    @Operation(
            summary = "Прочитать всех сотрудников",
            description = "Процесс создания списка всех сотрудников, сохраненных в базе данных"
    )
    public List<SotrudnikDto> procitatVseh() {
        return servise.procitatVseh();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "прочитать одного сотрудника по ИД",
            description = "процесс вывода одного сотрудника с его параметрами из базы данных по его идентификатору")

    public SotrudnikDto procitatPoId(@PathVariable
                                     @Parameter(description = "Уникальный идентификатор сотрудника")
                                     Long id) {
        return servise.procitatPoId(id);
    }

    @PutMapping
    @Operation(
            summary = "обновить сотрудника по ИД",
            description = "процесс изменения параметров одного сотрудника в базе данных по его идентификатору")
    public void obnovitPoId(@RequestBody
                            @io.swagger.v3.oas.annotations.parameters.
                                    RequestBody(description = "Дто сотрудника с заполненным именем, логином и паролем")
                            SotrudnikDto sotrudnik) {
        servise.obnovitPoId(sotrudnik);

    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "удалить сотрудника по ИД",
            description = "процесс удаления сотрудника из базы данных по его идентификатору")
    public void udalitPoId(@PathVariable
                           @Parameter(description = "Уникальный идентификатор сотрудника")
                           Long id) {
        servise.udalitPoId(id);

    }

    @DeleteMapping("/by-imya/{imya}")
    @Operation(
            summary = "удалить сотрудника по имени",
            description = "процесс удаления сотрудника из базы данных по его имени")
    public void udalitPoImeni(@PathVariable
                              @Parameter(description = "Имя сотрудника")
                              String imya) {
        servise.udalitPoImeni(imya);

    }

}
