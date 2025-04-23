package di.aittr.pro_sotrudnikov.servise.mapping;

import di.aittr.pro_sotrudnikov.domen.dto.ProektDto;
import di.aittr.pro_sotrudnikov.domen.entity.Proekt;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SotrudnikMappingSernise.class, ZadacaMappingServise.class})

public interface ProektMappingServise {

    ProektDto mapEntityToDto(Proekt entity);

    @Mapping(target = "id", ignore = true)
    Proekt mahDtoToEntity(ProektDto dto);
}
