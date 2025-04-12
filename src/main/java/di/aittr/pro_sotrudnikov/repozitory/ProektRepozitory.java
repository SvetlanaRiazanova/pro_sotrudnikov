package di.aittr.pro_sotrudnikov.repozitory;

import di.aittr.pro_sotrudnikov.domen.entity.Proekt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProektRepozitory extends JpaRepository<Proekt, Long> {

    void deleteByName(String nazvanie);
}
