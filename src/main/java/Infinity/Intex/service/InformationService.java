package Infinity.Intex.service;

import Infinity.Intex.dto.InformationDto;
import Infinity.Intex.dto.ResponseDto;

public interface InformationService {
    ResponseDto<InformationDto> get();
    ResponseDto<Void> update(InformationDto informationDto,Integer id);
}
