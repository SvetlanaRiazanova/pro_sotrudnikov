package di.aittr.pro_sotrudnikov.servise;

import di.aittr.pro_sotrudnikov.domen.entity.Role;
import di.aittr.pro_sotrudnikov.exeption_handling.handling.exeptions.RoleNeNaidenExeption;
import di.aittr.pro_sotrudnikov.repozitory.RoleRepozitory;
import di.aittr.pro_sotrudnikov.servise.interfaces.RoleServise;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleServise {

    public final RoleRepozitory repozitory;

    public RoleServiceImpl(RoleRepozitory repozitory) {
        this.repozitory = repozitory;
    }

    @Override
    public Role procitatPoNaimenovanie(String naimenovanie) {
        return repozitory.findByNaimenovanie(naimenovanie).orElseThrow(
                () -> new RoleNeNaidenExeption(naimenovanie)
        );
    }
}
