package di.aittr.pro_sotrudnikov.servise.interfaces;

import di.aittr.pro_sotrudnikov.domen.dto.SotrudnikDto;
import di.aittr.pro_sotrudnikov.domen.entity.Sotrudnik;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface SotrudnikServise extends UserDetailsService {

    //    создать сотрудника в БД
    //    прочитать всех сотрудников
    //    прочитать одного сотрудника по ИД
    //    обновить сотрудника по ИД
    //    удалить сотрудника по ИД
    //    удалить сотрудника по имени

    SotrudnikDto sozdat(SotrudnikDto sotrudnik);
    List<SotrudnikDto> procitatVseh();
    SotrudnikDto procitatPoId(Long id);
    void obnovitPoId(SotrudnikDto sotrudnik);
    void udalitPoId(Long id);
    void udalitPoImeni(String imya);
    Sotrudnik procitatEntityPoId(Long sotrudnikId);
    void register(Sotrudnik sotrudnik);
    void confirmation(String code);
}
