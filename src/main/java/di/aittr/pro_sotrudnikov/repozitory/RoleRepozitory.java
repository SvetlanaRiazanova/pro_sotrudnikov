package di.aittr.pro_sotrudnikov.repozitory;

import di.aittr.pro_sotrudnikov.domen.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepozitory extends JpaRepository<Role, Long> {

    Optional<Role> findByNaimenovanie(String naimenovanie);
}
