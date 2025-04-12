package di.aittr.pro_sotrudnikov.servise.mapping;

import di.aittr.pro_sotrudnikov.domen.entity.Proekt;
import di.aittr.pro_sotrudnikov.repozitory.ProektRepozitory;
import di.aittr.pro_sotrudnikov.servise.interfaces.ProektServise;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProektServiseImpl implements ProektServise {

    public final ProektRepozitory repozitory;

    public ProektServiseImpl(ProektRepozitory repozitory) {
        this.repozitory = repozitory;
    }

    @Override
    public Proekt sozdat(Proekt proekt) {
        return repozitory.save(proekt);
    }

    @Override
    public List<Proekt> procitatVseh() {
        return repozitory.findAll();
    }

    @Override
    public Proekt procitatPoId(Long id) {
        return repozitory.findById(id).orElse(null);
    }

    @Override
    public void obnovitPoId(Proekt proekt) {
        Long id = proekt.getId();
        Proekt sushestvProekt = procitatPoId(id);
           sushestvProekt.setNazvanie(proekt.getNazvanie());

    }

    @Override
    public void udalitPoId(Long id) {
        repozitory.deleteById(id);

    }

    @Override
    public void udalitPoNazvaniyu(String nazvanie) {
        repozitory.deleteByName(nazvanie);

    }
}
