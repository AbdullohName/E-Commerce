package Infinity.Intex.mapper;

import Infinity.Intex.dto.CategoryDto;
import Infinity.Intex.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryDto categoryDto);
    CategoryDto toDto(Category category);
}
