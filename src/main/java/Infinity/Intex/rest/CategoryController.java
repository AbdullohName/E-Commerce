package Infinity.Intex.rest;

import Infinity.Intex.dto.CategoryDto;
import Infinity.Intex.dto.ResponseDto;
import Infinity.Intex.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RequiredArgsConstructor
@RequestMapping("/category")
@RestController
public class CategoryController {
    private final CategoryService service;
    @PostMapping()
    public ResponseDto<Void> add(@RequestBody @Valid CategoryDto categoryDto) {
        return service.add(categoryDto);
    }
    @PutMapping("/{id}")
    public ResponseDto<Void> update(@RequestBody CategoryDto categoryDto,@PathVariable("id") Integer id) {
        return service.update(categoryDto,id);
    }
    @DeleteMapping("/{id}")
    public ResponseDto<Void> deleteById(@PathVariable Integer id) {
        return service.deleteById(id);
    }
    @GetMapping
    public ResponseDto<List<CategoryDto>> getAll() {
        return service.getAll();
    }
}
