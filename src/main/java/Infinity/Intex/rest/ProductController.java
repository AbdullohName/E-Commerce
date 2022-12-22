package Infinity.Intex.rest;

import Infinity.Intex.dto.ProductDto;
import Infinity.Intex.dto.ResponseDto;
import Infinity.Intex.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;
    @GetMapping("/{categoryId}")
    public ResponseDto<List<ProductDto>> getProductByCategoryId(@PathVariable Integer categoryId) {
        return service.getProductByCategoryId(categoryId);
    }
    @PostMapping("/add")
    public ResponseDto<String> addProduct(@RequestPart(name = "productDto") ProductDto productDto, @RequestPart(name = "image") MultipartFile image) {
        return service.addProduct(productDto, image);
    }
    @PutMapping("/{id}")
    public ResponseDto<Void> updateProduct(@RequestBody ProductDto productDto,@PathVariable("id") Integer id) {
        return service.updateProduct(productDto,id);
    }
    @GetMapping("/by-id/{id}")
    public ResponseDto<ProductDto> oneById(@PathVariable Integer id) {
        return service.oneById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseDto<Void> deleteById(@PathVariable Integer id) {
        return service.deleteById(id);
    }

    @GetMapping("by-status")
    public ResponseDto<List<ProductDto>> getProductByStatus(@RequestParam String status) {
        return service.getProductByStatus(status);
    }

    @GetMapping
    public ResponseDto<List<ProductDto>> getAll() {
        return service.getAll();
    }

    @GetMapping("/pagination")
    public ResponseDto<List<ProductDto>> pagination(@RequestParam(required = false) Integer page,
                                                    @RequestParam(required = false) Integer size) {
        return service.pagination(page,size);
    }

    @GetMapping("/name")
    public ResponseDto<ProductDto> getByName(@RequestParam(required = false) String name) {
        return service.getByName(name);
    }
}
