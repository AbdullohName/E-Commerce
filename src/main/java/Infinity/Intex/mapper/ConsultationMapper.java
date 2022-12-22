package Infinity.Intex.mapper;

import Infinity.Intex.dto.ConsultationDto;
import Infinity.Intex.model.Consultation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsultationMapper {
    Consultation toEntity(ConsultationDto consultationDto);
    ConsultationDto toDto(Consultation consultation);
}
