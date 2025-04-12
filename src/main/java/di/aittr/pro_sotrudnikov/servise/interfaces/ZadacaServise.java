package di.aittr.pro_sotrudnikov.servise.interfaces;

import di.aittr.pro_sotrudnikov.domen.entity.Zadaca;

import java.util.List;

public interface ZadacaServise {

    Zadaca sozdat(Zadaca zadaca);
    List<Zadaca> procitatVseh();
    Zadaca procitatPoId(Long id);
    void obnovitPoId(Zadaca zadaca);
    void udalitPoId(Long id);
    void udalitPoNazvaniyu(String nazvanie);
}
