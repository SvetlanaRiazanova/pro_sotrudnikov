package di.aittr.pro_sotrudnikov.controller;

import di.aittr.pro_sotrudnikov.domen.dto.ProektDto;
import di.aittr.pro_sotrudnikov.servise.interfaces.ProektServise;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proekti")
@Tag(name = "Proekt controller", description = "Контроллер для различных операций с проектами")
public class ProektController {

    public final ProektServise servise;

    public ProektController(ProektServise servise) {
        this.servise = servise;
    }

    //REST API:
    //создать проект в БД
    //POST -> http://12.34.56.78:8888/proekti (в виде JSON)

    //прочитать все проекты
    //GET -> http://12.34.56.78:8888/proekti

    //прочитать один проект по ИД
    //GET -> http://12.34.56.78:8888/proekti/3

    //обновить проект по ИД (ИД отправлять в теле)
    //PUT -> http://12.34.56.78:8888/proekti

    //удалить проект по ИД
    //DELETE -> http://12.34.56.78:8888/proekti/3

    //удалить проект по названию
    //DELETE -> http://12.34.56.78:8888/proekti/by-nazvanie/magazin

    //Добавить задачу в проект
    //PUT -> http://12.34.56.78:8888/proekti/2/add-zadaca/3

    //Удалить задачу из проекта
    //DELETE -> http://12.34.56.78:8888/proekti/2/delete-zadaca/3

    //Очистить проект от задач
    //DELETE -> http://12.34.56.78:8888/proekti

    @PostMapping
    @Operation(
            summary = "Создать проект в БД",
            description = "Процесс сохранения проекта в базу данных")
    public ProektDto sozdat(@RequestBody
                            @io.swagger.v3.oas.annotations.parameters.
                                    RequestBody(description = "Дто проекта с заполненным названием, описанием и списком сотрудников")
                            ProektDto proekt,
                            @AuthenticationPrincipal String username) {
        return servise.sozdat(proekt, username);
    }

    @GetMapping
    @Operation(
            summary = "Прочитать все проекты",
            description = "Процесс создания списка всех проектов, сохраненных в базе данных"
    )
    public List<ProektDto> procitatVseh() {
        return servise.procitatVseh();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Прочитать один проект по ИД",
            description = "Процесс вывода одного проекта с его параметрами из базы данных")
    public ProektDto procitatPoId(@PathVariable
                                  @Parameter(description = "Уникальный идентификатор проекта")
                                  Long id) {
        return servise.procitatPoId(id);
    }

    @PutMapping
    @Operation(
            summary = "Обновить проект по ИД",
            description = "Процесс изменения параметров одного проекта в базе данных по его идентификатору")
    public void obnovitPoId(@RequestBody
                            @io.swagger.v3.oas.annotations.parameters.
                                    RequestBody(description = "Дто проекта с заполненным названием и описанием и списком сотрудников")
                            ProektDto proekt) {
        servise.obnovitPoId(proekt);

    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удалить проект по ИД",
            description = "Процесс удаления проекта из базы данных по его идентификатору")
    public void udalitPoId(@PathVariable
                           @Parameter(description = "Уникальный идентификатор проекта")
                           Long id) {
        servise.udalitPoId(id);

    }

    @DeleteMapping("/by-nazvanie/{nazvanie}")
    @Operation(
            summary = "Удалить проект по названию",
            description = "Процесс удаления проекта из базы данных по его названию")
    public void udalitPoNazvaniyu(@PathVariable
                                  @Parameter(description = "Название проекта")
                                  String nazvanie) {
        servise.udalitPoNazvaniyu(nazvanie);

    }

    @PutMapping("/{proektId}/add-zadaca/{zadacaId}")
    @Operation(
            summary = "Добавить задачу в проект",
            description = "Процесс добавления задачи в проект по их идентификаторам")
    public void dobavitZadacuVproektPoId(
            @PathVariable
            @Parameter(description = "Уникальный идентификатор проекта")
            Long proektId,
            @PathVariable
            @Parameter(description = "Уникальный идентификатор задачи")
            Long zadacaId
    ) {
        servise.dobavitZadacuVproektPoId(proektId, zadacaId);
    }

    @DeleteMapping("/{proektId}/delete-zadaca/{zadacaId}")
    @Operation(
            summary = "Удалить задачу из проекта",
            description = "Процесс удаления задачи из проекта по их идентификаторам")
    public void udalitZadacuIzProektaPoId(
            @PathVariable
            @Parameter(description = "Уникальный идентификатор проекта")
            Long proektId,
            @PathVariable
            @Parameter(description = "Уникальный идентификатор задачи")
            Long zadacaId) {
        servise.udalitZadacuIzProektaPoId(proektId, zadacaId);
    }

    @DeleteMapping("/{proektId}/clear")
    @Operation(
            summary = "Очистить проект от задач",
            description = "процесс удаления задач из проекта по его идентификатору")
    public void ocistitProektOtZadac(@PathVariable
                                     @Parameter(description = "Уникальный идентификатор проекта")
                                     Long proektId) {
        servise.ocistitProektOtZadac(proektId);
    }

}
