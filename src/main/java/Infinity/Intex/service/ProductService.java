package Infinity.Intex.service;

import Infinity.Intex.dto.ProductDto;
import Infinity.Intex.dto.ResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    ResponseDto<List<ProductDto>> getProductByCategoryId(Integer id);
    ResponseDto<String> addProduct(ProductDto productDto, MultipartFile image);
    ResponseDto<Void> updateProduct(ProductDto productDto,Integer id);
    ResponseDto<ProductDto> oneById(Integer id);
    ResponseDto<Void> deleteById(Integer id);
    ResponseDto<List<ProductDto>> getProductByStatus(String status);
    ResponseDto<List<ProductDto>> getAll();

    ResponseDto<List<ProductDto>> pagination(Integer p, Integer s);

    ResponseDto<ProductDto> getByName(String name);
    Boolean orderProduct(ProductDto productDto);
}
