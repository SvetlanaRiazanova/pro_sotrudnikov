package di.aittr.pro_sotrudnikov.servise;

import di.aittr.pro_sotrudnikov.domen.dto.ProektDto;
import di.aittr.pro_sotrudnikov.domen.entity.Proekt;
import di.aittr.pro_sotrudnikov.domen.entity.Sotrudnik;
import di.aittr.pro_sotrudnikov.domen.entity.Zadaca;
import di.aittr.pro_sotrudnikov.repozitory.ProektRepozitory;
import di.aittr.pro_sotrudnikov.servise.interfaces.ProektServise;
import di.aittr.pro_sotrudnikov.servise.interfaces.SotrudnikServise;
import di.aittr.pro_sotrudnikov.servise.interfaces.ZadacaServise;
import di.aittr.pro_sotrudnikov.servise.mapping.ProektMappingServise;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProektServiseImpl implements ProektServise {

    private final ProektRepozitory repozitory;
    private final ProektMappingServise mappingServise;
    private final SotrudnikServise sotrudnikServise;
    private final ZadacaServise zadacaServise;


    public ProektServiseImpl(ProektRepozitory repozitory, ProektMappingServise mappingServise,
                             SotrudnikServiseImpl sotrudnikServise, ZadacaServise zadacaServise) {
        this.repozitory = repozitory;
        this.mappingServise = mappingServise;
        this.sotrudnikServise = sotrudnikServise;
        this.zadacaServise = zadacaServise;


    }

    @Transactional
    @Override
    public ProektDto sozdat(ProektDto dto, String username) {
        Proekt entity = mappingServise.mahDtoToEntity(dto);
        entity.setAvtorProekta((Sotrudnik) sotrudnikServise.loadUserByUsername(username));
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
        Proekt proekt = procitatEntityPoId(id);
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
    public Proekt procitatEntityPoId(Long proektId) {
        Proekt proekt = repozitory.findById(proektId).orElse(null);
        return proekt;

    }

    @Transactional
    @Override
    public void dobavitZadacuVproektPoId(Long proektId, Long zadacaId) {
        Proekt proekt = procitatEntityPoId(proektId);
        Zadaca zadaca = zadacaServise.procitatEntityPoId(zadacaId);
        zadaca.setProekt(proekt);
        proekt.getSpisokZadac().add(zadaca);

    }

    @Transactional
    @Override
    public void udalitZadacuIzProektaPoId(Long proektId, Long zadacaId) {
        Proekt proekt = procitatEntityPoId(proektId);
        proekt.getSpisokZadac().removeIf(x -> x.getId().equals(zadacaId));

    }

    @Transactional
    @Override
    public void ocistitProektOtZadac(Long proektId) {
        Proekt proekt = procitatEntityPoId(proektId);
        proekt.getSpisokZadac().clear();

    }


}
