package di.aittr.pro_sotrudnikov.servise.mapping;

import di.aittr.pro_sotrudnikov.domen.entity.Sotrudnik;
import di.aittr.pro_sotrudnikov.repozitory.SotrudnikRepozitory;
import di.aittr.pro_sotrudnikov.servise.interfaces.SotrudnikServise;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SotrudnikServiseImpl implements SotrudnikServise {

    private final SotrudnikRepozitory repozitory;

    public SotrudnikServiseImpl(SotrudnikRepozitory repozitory) {
        this.repozitory = repozitory;
    }

    @Override
    public Sotrudnik sozdat(Sotrudnik sotrudnik) {
        return repozitory.save(sotrudnik);
    }

    @Override
    public List<Sotrudnik> procitatVseh() {
        return repozitory.findAll();
    }

    @Override
    public Sotrudnik procitatPoId(Long id) {
        return repozitory.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void obnovitPoId(Sotrudnik sotrudnik) {
        Long id = sotrudnik.getId();
        Sotrudnik sushestvSotrudnik = procitatPoId(id);
        sushestvSotrudnik.setImya(sotrudnik.getImya());

    }

    @Override
    public void udalitPoId(Long id) {
        repozitory.deleteById(id);

    }

    @Transactional
    @Override
    public void udalitPoImeni(String imya) {
        repozitory.deleteByImya(imya);

    }
}
