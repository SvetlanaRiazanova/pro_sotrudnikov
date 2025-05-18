package di.aittr.pro_sotrudnikov.controller;

import di.aittr.pro_sotrudnikov.domen.dto.ZadacaDto;
import di.aittr.pro_sotrudnikov.servise.interfaces.ZadacaServise;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zadaci")
@Tag(name = "Zadaca controller", description = "Контроллер для различных операций с задачами")
public class ZadacaController {

    private final ZadacaServise servise;

    public ZadacaController(ZadacaServise servise) {
        this.servise = servise;
    }

    //REST API:
    //создать задачу в БД
    //POST -> http://12.34.56.78:8888/zadaci ( в виде JSON )

    //прочитать все задачи
    //GET -> http://12.34.56.78:8888/zadaci

    //прочитать одну задачу по ИД
    //GET -> http://12.34.56.78:8888/zadaci/3

    //обновить задачу по ИД (ИД отправлять в теле)
    //PUT -> http://12.34.56.78:8888/zadaci

    //удалить задачу по ИД
    //DELETE -> http://12.34.56.78:8888/zadaci/3

    //удалить задачу по названию
    //DELETE -> http://12.34.56.78:8888/zadaci/by-nazvanie/zaklucitDogovor

    //Добавить исполнителя в задачу
    //PUT -> http://12.34.56.78:8888/zadaca/2/add-sotrudnik/3

    //Удалить исполнителя из задачи
    //DELETE -> http://12.34.56.78:8888/proekti/2/delete-sotrudnik/3

    //Очистить задачу от исполнителей
    //DELETE -> http://12.34.56.78:8888/zadaca

    @PostMapping
    @Operation(
            summary = "Создать задачу в БД",
            description = "Процесс сохранения задачи в базу данных")
    public ZadacaDto sozdat(@RequestBody
                            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                    description = "Дто задачи с заполненным названием и описанием")
                            ZadacaDto zadaca) {
        return servise.sozdat(zadaca);
    }

    @GetMapping
    @Operation(
            summary = "Прочитать все задачи",
            description = "Процесс создания списка всех задач, сохраненных в базе данных"
    )
    public List<ZadacaDto> procitatVseh() {
        return servise.procitatVseh();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Прочитать одну задачу по ИД",
            description = "Процесс вывода одной задачи с её параметрами из базы данных")
    public ZadacaDto procitatPoId(@PathVariable
                                  @Parameter(description = "Уникальный идентификатор задачи")
                                  Long id) {
        return servise.procitatPoId(id);
    }

    @PutMapping
    @Operation(
            summary = "Обновить задачу по ИД",
            description = "Процесс изменения параметров одной задачи в базе данных по его идентификатору")
    public void obnovitPoId(@RequestBody
                            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                    description = "Дто задачи с заполненным идентификатором, названием и(или)описанием")
                            ZadacaDto zadaca) {
        servise.obnovitPoId(zadaca);

    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удалить задачу по ИД",
            description = "Процесс удаления одной задачи в базе данных по её идентификатору")
    public void udalitPoId(@PathVariable
                           @Parameter(description = "Уникальный идентификатор задачи")
                           Long id) {
        servise.udalitPoId(id);

    }

    @DeleteMapping("/by-nazvanie/{nazvanie}")
    @Operation(
            summary = "Удалить задачу по названию",
            description = "Процесс удаления одной задачи в базе данных по её названию")
    public void udalitPoNazvaniyu(@PathVariable
                                  @Parameter(description = "Название задачи")
                                  String nazvanie) {
        servise.udalitPoNazvaniyu(nazvanie);

    }

    @PutMapping("/{zadacaId}/add-sotrudnik/{sotrudnikId}")
    @Operation(
            summary = "Добавить сотрудника в задачу",
            description = "Процесс добавления сотрудника в задачу по их идентификаторам")
    public void dobavitSotrudnikaVzadacuPoId(
            @PathVariable
            @Parameter(description = "Уникальный идентификатор задачи")
            Long zadacaId,
            @PathVariable
            @Parameter(description = "Уникальный идентификатор сотрудника")
            Long sotrudnikId
    ) {
        servise.dobavitSotrudnikaVzadacuPoId(zadacaId, sotrudnikId);
    }

    @DeleteMapping("/{zadacaId}/delete-sotrudnik/{sotrudnikId}")
    @Operation(
            summary = "Удалить сотрудника из задачи",
            description = "Процесс удаления сотрудника из задачи по их идентификаторам")
    public void udalitSotrudnikaIzZadaciPoId(
            @PathVariable
            @Parameter(description = "Уникальный идентификатор задачи")
            Long zadacaId,
            @PathVariable
            @Parameter(description = "Уникальный идентификатор сотрудника")
            Long sotrudnikId) {
        servise.udalitSotrudnikaIzZadaciPoId(zadacaId, sotrudnikId);
    }

    @DeleteMapping("/{zadacaId}/clear")
    @Operation(
            summary = "Очистить задачу от сотрудников",
            description = "процесс удаления сотрудников из задачи по её идентификатору")
    public void ocistitZadacuOtSotrudnikov(@PathVariable
                                           @Parameter(description = "Уникальный идентификатор задачи")
                                           Long zadacaId) {
        servise.ocistitZadacuOtSotrudnikov(zadacaId);
    }

}
