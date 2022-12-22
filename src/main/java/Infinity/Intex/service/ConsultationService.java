package Infinity.Intex.service;

import Infinity.Intex.dto.ConsultationDto;
import Infinity.Intex.dto.ResponseDto;
import Infinity.Intex.model.Consultation;

import java.util.List;

public interface ConsultationService {
    ResponseDto<List<ConsultationDto>> getAll();
    ResponseDto<Void> deleteById(Integer id);
    ResponseDto<Void> changeIsViewById(Integer id);
    ResponseDto<Void> update(ConsultationDto consultationDto,Integer id);
    ResponseDto<ConsultationDto> getByName(String name);
    ResponseDto<String> add(ConsultationDto consultationDto);
}
