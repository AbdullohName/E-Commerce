package Infinity.Intex.service.Impl;

import Infinity.Intex.dto.CategoryDto;
import Infinity.Intex.dto.ResponseDto;
import Infinity.Intex.mapper.CategoryMapper;
import Infinity.Intex.model.Category;
import Infinity.Intex.repository.CategoryRepository;
import Infinity.Intex.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public ResponseDto<Void> add(CategoryDto categoryDto) {
        try {
            repository.save(mapper.toEntity(categoryDto));
            return ResponseDto.<Void>builder()
                    .code(0)
                    .message("OK")
                    .success(true)
                    .build();
        } catch (Exception e) {
            return ResponseDto.<Void>builder()
                    .code(-3)
                    .success(false)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<Void> update(CategoryDto categoryDto,Integer id) {
        try {
            if(repository.existsById(id)) {
                categoryDto.setId(id);
                repository.save(mapper.toEntity(categoryDto));
                return ResponseDto.<Void>builder()
                        .code(0)
                        .message("OK")
                        .success(true)
                        .build();
            }
            return ResponseDto.<Void>builder()
                    .code(-2)
                    .message("Not Found Category In DB")
                    .success(false)
                    .build();
        } catch (Exception e) {
            return ResponseDto.<Void>builder()
                    .code(-3)
                    .success(false)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<Void> deleteById(Integer id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseDto.<Void>builder()
                    .code(0)
                    .message("OK")
                    .success(true)
                    .build();
        }
        return ResponseDto.<Void>builder()
                .code(-2)
                .success(false)
                .message("Id Not Found in DB")
                .build();
    }

    @Override
    public ResponseDto<List<CategoryDto>> getAll() {
        List<Category> categories = repository.findAll();
        List<CategoryDto> categoryDtos = categories.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseDto.<List<CategoryDto>>builder()
                .code(0)
                .message("OK")
                .success(true)
                .data(categoryDtos)
                .build();
    }


}
