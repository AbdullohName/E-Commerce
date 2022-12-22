package Infinity.Intex.service;

import Infinity.Intex.dto.CategoryDto;
import Infinity.Intex.dto.ResponseDto;

import java.util.List;

public interface CategoryService {
    ResponseDto<Void> add(CategoryDto categoryDto);
    ResponseDto<Void> update(CategoryDto categoryDto,Integer id);
    ResponseDto<Void> deleteById(Integer id);
    ResponseDto<List<CategoryDto>> getAll();
}
