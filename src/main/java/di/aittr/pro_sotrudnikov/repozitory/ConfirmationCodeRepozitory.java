package di.aittr.pro_sotrudnikov.repozitory;

import di.aittr.pro_sotrudnikov.domen.entity.ConfirmationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmationCodeRepozitory extends JpaRepository<ConfirmationCode, Long> {

    Optional<ConfirmationCode> findByCode(String code);
}
