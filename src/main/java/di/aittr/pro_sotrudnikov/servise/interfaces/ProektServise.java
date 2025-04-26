package di.aittr.pro_sotrudnikov.servise.interfaces;

import di.aittr.pro_sotrudnikov.domen.dto.ProektDto;
import di.aittr.pro_sotrudnikov.domen.entity.Proekt;

import java.util.List;

public interface ProektServise {

    ProektDto sozdat(ProektDto proekt, String username);
    List<ProektDto> procitatVseh();
    ProektDto procitatPoId(Long id);
    void obnovitPoId(ProektDto proekt);
    void udalitPoId(Long id);
    void udalitPoNazvaniyu(String nazvanie);
    void dobavitZadacuVproektPoId(Long proektId,Long zadacaId);
    void udalitZadacuIzProektaPoId(Long proektId,Long zadacaId);
    void ocistitProektOtZadac(Long proektId);
    Proekt procitatEntityPoId(Long proektId);
}
