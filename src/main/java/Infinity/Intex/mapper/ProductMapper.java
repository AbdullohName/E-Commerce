package Infinity.Intex.mapper;

import Infinity.Intex.dto.ProductDto;
import Infinity.Intex.model.Product;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductDto productDto);
    ProductDto toDto(Product product);
}
