package di.aittr.pro_sotrudnikov.servise;

import di.aittr.pro_sotrudnikov.domen.dto.ProektDto;
import di.aittr.pro_sotrudnikov.domen.entity.Proekt;
import di.aittr.pro_sotrudnikov.repozitory.ProektRepozitory;
import di.aittr.pro_sotrudnikov.repozitory.ZadacaRepozitory;
import di.aittr.pro_sotrudnikov.servise.interfaces.ProektServise;
import di.aittr.pro_sotrudnikov.servise.mapping.ProektMappingServise;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProektServiseImpl implements ProektServise {

    private final ProektRepozitory repozitory;
    private final ProektMappingServise mappingServise;
    private final SotrudnikServiseImpl sotrudnikServise;



    public ProektServiseImpl(ProektRepozitory repozitory, ProektMappingServise mappingServise, SotrudnikServiseImpl sotrudnikServise, ZadacaServiseImpl zadacaServise, ZadacaRepozitory zadacaRepozitory) {
        this.repozitory = repozitory;
        this.mappingServise = mappingServise;
        this.sotrudnikServise = sotrudnikServise;

    }

    @Transactional
    @Override
    public ProektDto sozdat(ProektDto dto) {
        dto.setAvtorProekta(sotrudnikServise.procitatPoId(3L));
        Proekt entity = mappingServise.mahDtoToEntity(dto);
        entity = repozitory.save(entity);
        return mappingServise.mapEntityToDto(entity);
    }

    @Override
    public List<ProektDto> procitatVseh() {
        return repozitory.findAll()
                .stream()
                .map(mappingServise::mapEntityToDto)
                .toList();
    }

    @Override
    public ProektDto procitatPoId(Long id) {
        Proekt proekt = repozitory.findById(id).orElse(null);
        return mappingServise.mapEntityToDto(proekt);
    }

    @Transactional
    @Override
    public void obnovitPoId(ProektDto proekt) {
        Long id = proekt.getId();
        ProektDto sushestvProekt = procitatPoId(id);
        sushestvProekt.setNazvanie(proekt.getNazvanie());

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
    public void dobavitZadacuVproektPoId(Long proektId, Long zadacaId) {
        repozitory.findById(zadacaId);
    }

    @Transactional
    @Override
    public void udalitZadacuIzProektaPoId(Long proektId, Long zadacaId) {
        ProektDto proekt = procitatPoId(proektId);
        proekt.getSpisokZadac().removeIf(x-> x.getId().equals(zadacaId));

    }

        @Override
        public void ocistitProektOtZadac (Long proektId){
            repozitory.deleteAll();

        }

}
