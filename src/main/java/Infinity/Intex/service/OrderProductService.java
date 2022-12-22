package Infinity.Intex.service;

import Infinity.Intex.dto.ConsultationDto;
import Infinity.Intex.dto.OrderProductDto;
import Infinity.Intex.dto.ResponseDto;

import java.util.List;

public interface OrderProductService {
    ResponseDto<List<OrderProductDto>> getAll();
    ResponseDto<Void> add(OrderProductDto orderProductDto);
    ResponseDto<Void> deleteById(Integer id);
    ResponseDto<Void> changeIsViewById(Integer id);
    ResponseDto<Void> update(OrderProductDto orderProductDto,Integer id);
    ResponseDto<OrderProductDto> getByName(String name);
}
