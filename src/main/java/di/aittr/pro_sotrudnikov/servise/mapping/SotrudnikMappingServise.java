package di.aittr.pro_sotrudnikov.servise.mapping;

import di.aittr.pro_sotrudnikov.domen.dto.SotrudnikDto;
import di.aittr.pro_sotrudnikov.domen.entity.Sotrudnik;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SotrudnikMappingServise {

    @Mapping(target = "password", constant = "hidden")
    SotrudnikDto mapEntityToDto(Sotrudnik entity);

    @Mapping(target = "id", ignore = true)
    Sotrudnik mahDtoToEntity(SotrudnikDto dto);


}
