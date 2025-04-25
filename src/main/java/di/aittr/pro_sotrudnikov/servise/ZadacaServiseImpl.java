package di.aittr.pro_sotrudnikov.servise;

import di.aittr.pro_sotrudnikov.domen.dto.SotrudnikDto;
import di.aittr.pro_sotrudnikov.domen.dto.ZadacaDto;
import di.aittr.pro_sotrudnikov.domen.entity.Sotrudnik;
import di.aittr.pro_sotrudnikov.domen.entity.Zadaca;
import di.aittr.pro_sotrudnikov.repozitory.ZadacaRepozitory;
import di.aittr.pro_sotrudnikov.servise.interfaces.SotrudnikServise;
import di.aittr.pro_sotrudnikov.servise.interfaces.ZadacaServise;
import di.aittr.pro_sotrudnikov.servise.mapping.SotrudnikMappingSernise;
import di.aittr.pro_sotrudnikov.servise.mapping.ZadacaMappingServise;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZadacaServiseImpl implements ZadacaServise {

    private final ZadacaRepozitory repozitory;
    private final ZadacaMappingServise mappingServise;
    private final SotrudnikServise sotrudnikServise;
    private final SotrudnikMappingSernise sotrudnikMappingSernise;

    public ZadacaServiseImpl(ZadacaRepozitory repozitory, ZadacaMappingServise mappingServise, SotrudnikServise sotrudnikServise, SotrudnikMappingSernise sotrudnikMappingSernise) {
        this.repozitory = repozitory;
        this.mappingServise = mappingServise;
        this.sotrudnikServise = sotrudnikServise;
        this.sotrudnikMappingSernise = sotrudnikMappingSernise;
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

    @Transactional
    @Override
    public void dobavitSotrudnikaVzadacuPoId(Long zadacaId, Long sotrudnikId) {
        ZadacaDto zadaca = procitatPoId(zadacaId);
        Zadaca entityZadaca = mappingServise.mahDtoToEntity(zadaca);
        SotrudnikDto sotrudnik = sotrudnikServise.procitatPoId(sotrudnikId);
        Sotrudnik entitySotrudnik = sotrudnikMappingSernise.mahDtoToEntity(sotrudnik);
        entityZadaca.getSpisokSotrudnikov().add(entitySotrudnik);
    }

    @Transactional
    @Override
    public void udalitSotrudnikaIzZadaciPoId(Long zadacaId, Long sotrudnikId) {
        ZadacaDto zadaca = procitatPoId(zadacaId);
        Zadaca entityZadaca = mappingServise.mahDtoToEntity(zadaca);
        entityZadaca.getSpisokSotrudnikov().removeIf(x -> x.getId().equals(sotrudnikId));

    }

    @Transactional
    @Override
    public void ocistitZadacuOtSotrudnikov(Long zadacaId) {
        ZadacaDto zadaca = procitatPoId(zadacaId);
        Zadaca entityZadaca = mappingServise.mahDtoToEntity(zadaca);
        entityZadaca.getSpisokSotrudnikov().clear();

    }
}
