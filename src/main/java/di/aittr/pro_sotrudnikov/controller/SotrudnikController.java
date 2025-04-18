package di.aittr.pro_sotrudnikov.controller;

import di.aittr.pro_sotrudnikov.domen.entity.Sotrudnik;
import di.aittr.pro_sotrudnikov.servise.interfaces.SotrudnikServise;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sotrudniki")
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
    public Sotrudnik sozdat(@RequestBody Sotrudnik sotrudnik) {
        return servise.sozdat(sotrudnik);
    }

    @GetMapping
    public List<Sotrudnik> procitatVseh() {
        return servise.procitatVseh();
    }

    @GetMapping("/{id}")
    public Sotrudnik procitatPoId(@PathVariable Long id) {
        return servise.procitatPoId(id);
    }

    @PutMapping
    public void obnovitPoId(@RequestBody Sotrudnik sotrudnik) {
        servise.obnovitPoId(sotrudnik);

    }
    @DeleteMapping("/{id}")
    public void udalitPoId(@PathVariable Long id){
        servise.udalitPoId(id);

    }
    @DeleteMapping("/by-imya/{imya}")
    public void udalitPoImeni(@PathVariable String imya){
        servise.udalitPoImeni(imya);

    }

}
