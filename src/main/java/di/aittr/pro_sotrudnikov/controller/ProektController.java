package di.aittr.pro_sotrudnikov.controller;

import di.aittr.pro_sotrudnikov.domen.dto.ProektDto;
import di.aittr.pro_sotrudnikov.servise.interfaces.ProektServise;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proekti")
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
    public ProektDto sozdat(@RequestBody ProektDto proekt, @AuthenticationPrincipal String username) {
        return servise.sozdat(proekt);
    }

    @GetMapping
    public List<ProektDto> procitatVseh() {
        return servise.procitatVseh();
    }

    @GetMapping("/{id}")
    public ProektDto procitatPoId(@PathVariable Long id) {
        return servise.procitatPoId(id);
    }

    @PutMapping
    public void obnovitPoId(@RequestBody ProektDto proekt) {
        servise.obnovitPoId(proekt);

    }

    @DeleteMapping("/{id}")
    public void udalitPoId(@PathVariable Long id) {
        servise.udalitPoId(id);

    }

    @DeleteMapping("/by-nazvanie/{nazvanie}")
    public void udalitPoNazvaniyu(@PathVariable String nazvanie) {
        servise.udalitPoNazvaniyu(nazvanie);

    }

    @PutMapping("/{proektId}/add-zadaca/{zadacaId}")
    public void dobavitZadacuVproektPoId(
            @PathVariable Long proektId,
            @PathVariable Long zadacaId
    ) {
        servise.dobavitZadacuVproektPoId(proektId, zadacaId);
    }

    @DeleteMapping("/{proektId}/delete-zadaca/{zadacaId}")
    public void udalitZadacuIzProektaPoId(
            @PathVariable Long proektId,
            @PathVariable Long zadacaId) {
        servise.udalitZadacuIzProektaPoId(proektId, zadacaId);
    }

    @DeleteMapping("/{proektId}")
    public void ocistitProektOtZadac(@PathVariable Long proektId) {
        servise.ocistitProektOtZadac(proektId);
    }

}
