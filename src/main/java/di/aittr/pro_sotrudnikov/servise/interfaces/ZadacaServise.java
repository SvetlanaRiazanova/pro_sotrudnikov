package di.aittr.pro_sotrudnikov.servise.interfaces;

import di.aittr.pro_sotrudnikov.domen.dto.ZadacaDto;
import di.aittr.pro_sotrudnikov.domen.entity.Zadaca;

import java.util.List;

public interface ZadacaServise {

    ZadacaDto sozdat(ZadacaDto zadaca);
    List<ZadacaDto> procitatVseh();
    ZadacaDto procitatPoId(Long id);
    void obnovitPoId(ZadacaDto zadaca);
    void udalitPoId(Long id);
    void udalitPoNazvaniyu(String nazvanie);
    void dobavitSotrudnikaVzadacuPoId(Long zadacaId,Long sotrudnikId);
    void udalitSotrudnikaIzZadaciPoId(Long zadacaId,Long sotrudnikId);
    void ocistitZadacuOtSotrudnikov(Long zadacaId);
    Zadaca procitatEntityPoId(Long zadacaId);
}
