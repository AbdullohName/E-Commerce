package Infinity.Intex.mapper;

import Infinity.Intex.dto.InformationDto;
import Infinity.Intex.model.Information;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InformationMapper {
    Information toEntity(InformationDto informationDto);
    InformationDto toDto(Information information);
}
