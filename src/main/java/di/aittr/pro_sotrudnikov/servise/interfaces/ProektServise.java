package di.aittr.pro_sotrudnikov.servise.interfaces;

import di.aittr.pro_sotrudnikov.domen.dto.ProektDto;

import java.util.List;

public interface ProektServise {

    ProektDto sozdat(ProektDto proekt);
    List<ProektDto> procitatVseh();
    ProektDto procitatPoId(Long id);
    void obnovitPoId(ProektDto proekt);
    void udalitPoId(Long id);
    void udalitPoNazvaniyu(String nazvanie);
}
