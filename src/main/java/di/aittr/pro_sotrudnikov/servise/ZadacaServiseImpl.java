package di.aittr.pro_sotrudnikov.servise;

import di.aittr.pro_sotrudnikov.domen.dto.ZadacaDto;
import di.aittr.pro_sotrudnikov.domen.entity.Zadaca;
import di.aittr.pro_sotrudnikov.repozitory.ZadacaRepozitory;
import di.aittr.pro_sotrudnikov.servise.interfaces.ZadacaServise;
import di.aittr.pro_sotrudnikov.servise.mapping.ZadacaMappingServise;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZadacaServiseImpl implements ZadacaServise {

    private final ZadacaRepozitory repozitory;
    private final ZadacaMappingServise mappingServise;

    public ZadacaServiseImpl(ZadacaRepozitory repozitory, ZadacaMappingServise mappingServise) {
        this.repozitory = repozitory;
        this.mappingServise = mappingServise;
    }

    @Override
    public ZadacaDto sozdat(ZadacaDto dto) {
        Zadaca entity = mappingServise.mahDtoToEntity(dto);
        entity = repozitory.save(entity);
        return mappingServise.mapEntityToDto(entity);
    }

    @Override
    public List<ZadacaDto> procitatVseh() {
        return repozitory.findAll()
                .stream()
                .map(mappingServise::mapEntityToDto)
                .toList();
    }

    @Override
    public ZadacaDto procitatPoId(Long id) {
        Zadaca zadaca = repozitory.findById(id).orElse(null);
        return mappingServise.mapEntityToDto(zadaca);
    }

    @Transactional
    @Override
    public void obnovitPoId(ZadacaDto zadaca) {
        Long id = zadaca.getId();
        ZadacaDto sushestvZadaca = procitatPoId(id);
        sushestvZadaca.setOpisanie(zadaca.getOpisanie());

    }

    @Override
    public void udalitPoId(Long id) {
        repozitory.deleteById(id);

    }

    @Transactional
    @Override
    public void udalitPoNazvaniyu(String nazvanie) {
        repozitory.deleteByNazvanie(nazvanie);

    }

    @Override
    public void dobavitSotrudnikaVzadacuPoId(Long zadacaId, Long sotrudnikId) {
        repozitory.findById(sotrudnikId);
    }

    @Override
    public void udalitSotrudnikaIzZadaciPoId(Long zadacaId, Long sotrudnikId) {
        ZadacaDto zadaca = procitatPoId(zadacaId);
        zadaca.getSpisokSotrudnikov().removeIf(x-> x.getId().equals(sotrudnikId));

    }

    @Override
    public void ocistitZadacuOtSotrudnikov(Long zadacaId) {
        repozitory.deleteAll();

    }
}
