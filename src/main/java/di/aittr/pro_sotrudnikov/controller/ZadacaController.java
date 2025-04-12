package di.aittr.pro_sotrudnikov.controller;

import di.aittr.pro_sotrudnikov.domen.entity.Zadaca;
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

    @PostMapping
    public Zadaca sozdat(@RequestBody Zadaca zadaca) {
        return servise.sozdat(zadaca);
    }

    @GetMapping
    public List<Zadaca> procitatVseh() {
        return servise.procitatVseh();
    }

    @GetMapping("/id")
    public Zadaca procitatPoId(@PathVariable Long id) {
        return servise.procitatPoId(id);
    }

    @PutMapping
    public void obnovitPoId(@RequestBody Zadaca zadaca) {
        servise.obnovitPoId(zadaca);

    }
    @DeleteMapping("/id")
    public void udalitPoId(@PathVariable Long id){
        servise.udalitPoId(id);

    }
    @DeleteMapping("/by-nazvanie/{nazvanie}")
    public void udalitPoNazvaniyu(@PathVariable String nazvanie){
        servise.udalitPoNazvaniyu(nazvanie);

    }

}
