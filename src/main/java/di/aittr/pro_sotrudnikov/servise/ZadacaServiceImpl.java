package di.aittr.pro_sotrudnikov.servise;

import di.aittr.pro_sotrudnikov.domen.dto.ZadacaDto;
import di.aittr.pro_sotrudnikov.domen.entity.Zadaca;
import di.aittr.pro_sotrudnikov.exeption_handling.handling.exeptions.ZadacaValidacionExeption;
import di.aittr.pro_sotrudnikov.repozitory.ZadacaRepozitory;
import di.aittr.pro_sotrudnikov.servise.interfaces.SotrudnikServise;
import di.aittr.pro_sotrudnikov.servise.interfaces.ZadacaServise;
import di.aittr.pro_sotrudnikov.servise.mapping.ZadacaMappingServise;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZadacaServiceImpl implements ZadacaServise {

    private final ZadacaRepozitory repozitory;
    private final ZadacaMappingServise mappingServise;
    private final SotrudnikServise sotrudnikServise;

    public ZadacaServiceImpl(ZadacaRepozitory repozitory, ZadacaMappingServise mappingServise, SotrudnikServise sotrudnikServise) {
        this.repozitory = repozitory;
        this.mappingServise = mappingServise;
        this.sotrudnikServise = sotrudnikServise;

    }

    @Override
    public ZadacaDto sozdat(ZadacaDto dto) {

        try {
            Zadaca entity = mappingServise.mahDtoToEntity(dto);
            entity = repozitory.save(entity);
            return mappingServise.mapEntityToDto(entity);
        } catch (Exception e) {
            throw new ZadacaValidacionExeption(e);
        }

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
        Zadaca zadaca = procitatEntityPoId(id);
        return mappingServise.mapEntityToDto(zadaca);
    }

    @Transactional
    @Override
    public void obnovitPoId(ZadacaDto zadaca) {

        try {
            Long id = zadaca.getId();
            ZadacaDto sushestvZadaca = procitatPoId(id);
            sushestvZadaca.setOpisanie(zadaca.getOpisanie());
        } catch (Exception e) {
            throw new ZadacaValidacionExeption(e);
        }


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
    public Zadaca procitatEntityPoId(Long zadacaId) {
        Zadaca zadaca = repozitory.findById(zadacaId).orElse(null);
        return zadaca;
    }


    @Transactional
    @Override
    public void dobavitSotrudnikaVzadacuPoId(Long zadacaId, Long sotrudnikId) {
        Zadaca zadaca = procitatEntityPoId(zadacaId);
        zadaca.getSpisokSotrudnikov().add(sotrudnikServise.procitatEntityPoId(sotrudnikId));
    }

    @Transactional
    @Override
    public void udalitSotrudnikaIzZadaciPoId(Long zadacaId, Long sotrudnikId) {
        Zadaca zadaca = procitatEntityPoId(zadacaId);
        zadaca.getSpisokSotrudnikov().removeIf(x -> x.getId().equals(sotrudnikId));

    }

    @Transactional
    @Override
    public void ocistitZadacuOtSotrudnikov(Long zadacaId) {
        Zadaca zadaca = procitatEntityPoId(zadacaId);
        zadaca.getSpisokSotrudnikov().clear();

    }
}
