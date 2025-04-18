package di.aittr.pro_sotrudnikov.servise.interfaces;

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

    Sotrudnik sozdat(Sotrudnik sotrudnik);
    List<Sotrudnik> procitatVseh();
    Sotrudnik procitatPoId(Long id);
    void obnovitPoId(Sotrudnik sotrudnik);
    void udalitPoId(Long id);
    void udalitPoImeni(String imya);
}
