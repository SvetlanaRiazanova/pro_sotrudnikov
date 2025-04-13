package di.aittr.pro_sotrudnikov.servise.mapping;

import di.aittr.pro_sotrudnikov.domen.entity.Zadaca;
import di.aittr.pro_sotrudnikov.repozitory.ZadacaRepozitory;
import di.aittr.pro_sotrudnikov.servise.interfaces.ZadacaServise;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZadacaServiseImpl implements ZadacaServise {

    private final ZadacaRepozitory repozitory;

    public ZadacaServiseImpl(ZadacaRepozitory repozitory) {
        this.repozitory = repozitory;
    }

    @Override
    public Zadaca sozdat(Zadaca zadaca) {
        return repozitory.save(zadaca);
    }

    @Override
    public List<Zadaca> procitatVseh() {
        return repozitory.findAll();
    }

    @Override
    public Zadaca procitatPoId(Long id) {
        return repozitory.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void obnovitPoId(Zadaca zadaca) {
        Long id = zadaca.getId();
        Zadaca sushestvZadaca = procitatPoId(id);
        sushestvZadaca.setOpisanie(zadaca.getOpisanie());

    }

    @Override
    public void udalitPoId(Long id) {
        repozitory.deleteById(id);

    }

    @Override
    public void udalitPoNazvaniyu(String nazvanie) {
        repozitory.deleteByNazvanie(nazvanie);

    }
}
