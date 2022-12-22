package Infinity.Intex.mapper;

import Infinity.Intex.dto.OrderProductDto;
import Infinity.Intex.model.OrderProduct;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {
    OrderProduct toEntity(OrderProductDto orderProductDto);
    OrderProductDto toDto(OrderProduct orderProduct);
}
