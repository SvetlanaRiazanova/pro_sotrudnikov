package di.aittr.pro_sotrudnikov.controller;

import di.aittr.pro_sotrudnikov.domen.dto.ZadacaDto;
import di.aittr.pro_sotrudnikov.servise.interfaces.ZadacaServise;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zadaci")
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
    public ZadacaDto sozdat(@RequestBody ZadacaDto zadaca) {
        return servise.sozdat(zadaca);
    }

    @GetMapping
    public List<ZadacaDto> procitatVseh() {
        return servise.procitatVseh();
    }

    @GetMapping("/{id}")
    public ZadacaDto procitatPoId(@PathVariable Long id) {
        return servise.procitatPoId(id);
    }

    @PutMapping
    public void obnovitPoId(@RequestBody ZadacaDto zadaca) {
        servise.obnovitPoId(zadaca);

    }
    @DeleteMapping("/{id}")
    public void udalitPoId(@PathVariable Long id){
        servise.udalitPoId(id);

    }
    @DeleteMapping("/by-nazvanie/{nazvanie}")
    public void udalitPoNazvaniyu(@PathVariable String nazvanie){
        servise.udalitPoNazvaniyu(nazvanie);

    }
    @PutMapping("/{zadacaId}/add-sotrudnik/{sotrudnikId}")
    public void dobavitSotrudnikaVzadacuPoId(
            @PathVariable Long zadacaId,
            @PathVariable Long sotrudnikId
    ) {
        servise.dobavitSotrudnikaVzadacuPoId(zadacaId,sotrudnikId);
    }

    @DeleteMapping("/{zadacaId}/delete-sotrudnik/{sotrudnikId}")
    public void udalitSotrudnikaIzZadaciPoId(
            @PathVariable Long zadacaId,
            @PathVariable Long sotrudnikId) {
        servise.udalitSotrudnikaIzZadaciPoId(zadacaId,sotrudnikId);
    }

    @DeleteMapping("/{zadacaId}/clear")
    public void ocistitZadacuOtSotrudnikov(@PathVariable Long zadacaId) {
        servise.ocistitZadacuOtSotrudnikov(zadacaId);
    }

}
