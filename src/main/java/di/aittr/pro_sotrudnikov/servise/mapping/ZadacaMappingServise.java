package di.aittr.pro_sotrudnikov.servise.mapping;

import di.aittr.pro_sotrudnikov.domen.dto.ZadacaDto;
import di.aittr.pro_sotrudnikov.domen.entity.Zadaca;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = SotrudnikMappingSernise.class)
public interface ZadacaMappingServise {

    ZadacaDto mapEntityToDto(Zadaca entity);

    @Mapping(target = "id", ignore = true)
    Zadaca mahDtoToEntity(ZadacaDto dto);
}
