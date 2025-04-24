package di.aittr.pro_sotrudnikov.repozitory;

import di.aittr.pro_sotrudnikov.domen.entity.Zadaca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZadacaRepozitory extends JpaRepository<Zadaca, Long> {

    void deleteByNazvanie(String nazvanie);

    void deleteById(Long zadacaId, Long sotrudnikId);
}
