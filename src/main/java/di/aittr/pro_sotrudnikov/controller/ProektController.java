package di.aittr.pro_sotrudnikov.controller;

import di.aittr.pro_sotrudnikov.domen.entity.Proekt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proekti")
public class ProektController {

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

    @PostMapping
    public Proekt sozdat(@RequestBody Proekt proekt) {
        return null;
    }

    @GetMapping
    public List<Proekt> procitatVseh() {
        return null;
    }

    @GetMapping("/id")
    public Proekt procitatPoId(@PathVariable Long id) {
        return null;
    }

    @PutMapping
    public void obnovitPoId(@RequestBody Proekt proekt) {

    }
    @DeleteMapping("/id")
    public void udalitPoId(@PathVariable Long id){

    }
    @DeleteMapping("/by-nazvanie/{nazvanie}")
    public void udalitPoNazvaniyu(@PathVariable String nazvanie){

    }

}
