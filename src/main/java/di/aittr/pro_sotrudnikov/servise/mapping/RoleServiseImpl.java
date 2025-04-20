package di.aittr.pro_sotrudnikov.servise.mapping;

import di.aittr.pro_sotrudnikov.domen.entity.Role;
import di.aittr.pro_sotrudnikov.repozitory.RoleRepozitory;
import di.aittr.pro_sotrudnikov.servise.interfaces.RoleServise;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiseImpl implements RoleServise {

    public final RoleRepozitory repozitory;

    public RoleServiseImpl(RoleRepozitory repozitory) {
        this.repozitory = repozitory;
    }

    @Override
    public Role procitatPoNaimenovanie(String naimenovanie) {
        return repozitory.findByNaimenovanie(naimenovanie).orElse(null);
    }
}
