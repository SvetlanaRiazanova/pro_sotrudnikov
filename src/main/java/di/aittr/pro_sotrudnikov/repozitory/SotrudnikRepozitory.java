package di.aittr.pro_sotrudnikov.repozitory;

import di.aittr.pro_sotrudnikov.domen.entity.Sotrudnik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SotrudnikRepozitory extends JpaRepository<Sotrudnik, Long> {

    void deleteByImya(String imya);

}
