package di.aittr.pro_sotrudnikov.servise.interfaces;

import di.aittr.pro_sotrudnikov.domen.entity.Proekt;

import java.util.List;

public interface ProektServise {

    Proekt sozdat(Proekt proekt);
    List<Proekt> procitatVseh();
    Proekt procitatPoId(Long id);
    void obnovitPoId(Proekt proekt);
    void udalitPoId(Long id);
    void udalitPoNazvaniyu(String nazvanie);
}
