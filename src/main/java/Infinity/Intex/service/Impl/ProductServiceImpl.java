package Infinity.Intex.service.Impl;

import Infinity.Intex.dto.ProductDto;
import Infinity.Intex.dto.ResponseDto;
import Infinity.Intex.mapper.ProductMapper;
import Infinity.Intex.model.Admin;
import Infinity.Intex.model.Image;
import Infinity.Intex.model.Product;
import Infinity.Intex.repository.ProductRepository;
import Infinity.Intex.service.ImageService;
import Infinity.Intex.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static Infinity.Intex.utils.Util.UPLOAD_DIRECTORY;
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    private final ImageService imageService;
    @Override
    public ResponseDto<List<ProductDto>> getProductByCategoryId(Integer id) {
        List<Product> productList =  repository.findByCategoryId(id);
        if(!productList.isEmpty()) {
            List<ProductDto> productDtoList = productList.stream()
                    .map(mapper::toDto)
                    .collect(Collectors.toList());
            return ResponseDto.<List<ProductDto>>builder()
                    .code(0)
                    .message("OK")
                    .success(true)
                    .data(productDtoList)
                    .build();
        }
        return ResponseDto.<List<ProductDto>>builder()
                .code(-2)
                .message("Category not Found")
                .success(false)
                .build();
    }

    @Override
    @Transactional
    public ResponseDto<String> addProduct(ProductDto productDto, MultipartFile image) {
        Admin admin = (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!checkStatus(productDto.getStatus())) {
            return ResponseDto.<String>builder()
                    .code(-3)
                    .message("Status error")
                    .success(false)
                    .build();
        }
        try {
            productDto.setSellerId(admin.getId());
            Image save = imageService.save(image, productDto.getNameRu());
            productDto.setImage(save);
            repository.save(mapper.toEntity(productDto));
            return ResponseDto.<String>builder()
                    .code(0)
                    .success(true)
                    .message("OK")
                    .data("Your product has been saved")
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseDto.<String>builder()
                    .code(-2)
                    .message("FAILED")
                    .success(false)
                    .build();
        }
    }

    @Override
    public ResponseDto<Void> updateProduct(ProductDto productDto,Integer id) {
        try {
            Optional<Product> productOptional = repository.findById(id);
            if(productOptional.isPresent()) {
                productDto.setId(id);
                repository.save(mapper.toEntity(productDto));

                return ResponseDto.<Void>builder()
                        .code(0)
                        .message("OK")
                        .success(true)
                        .build();
            }
            return ResponseDto.<Void>builder()
                    .code(-2)
                    .message("Database Error")
                    .success(false)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseDto.<Void>builder()
                    .code(-2)
                    .message(e.getMessage())
                    .success(false)
                    .build();
        }
    }

    @Override
    public ResponseDto<ProductDto> oneById(Integer id) {
        try {
            Optional<Product> optional = repository.findById(id);
            if(optional.isPresent()) {
                return ResponseDto.<ProductDto>builder()
                        .code(0)
                        .message("OK")
                        .success(true)
                        .data(mapper.toDto(optional.get()))
                        .build();
            }
            return ResponseDto.<ProductDto>builder()
                    .code(-2)
                    .message("This id not Found")
                    .success(false)
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ProductDto>builder()
                    .code(-3)
                    .message(e.getMessage())
                    .success(false)
                    .build();
        }
    }

    @Override
    @Transactional
    public ResponseDto<Void> deleteById(Integer id) {
        Optional<Product> optional = repository.findById(id);
        if(optional.isPresent()) {
            repository.deleteById(id);
            Image image = optional.get().getImage();
            if(imageService.deleteByName(optional.get().getImage().getPhotoName())) {
                File file = new File(UPLOAD_DIRECTORY + "/" + image.getPhotoName());
                if(file.delete()) {
                    return ResponseDto.<Void>builder()
                            .code(0)
                            .message("OK")
                            .success(true)
                            .build();
                }
            }
        }
        return ResponseDto.<Void>builder()
                .code(-2)
                .message("Database Error, Id not Found Or image don't deleted")
                .success(false)
                .build();
    }



    @Override
    public ResponseDto<List<ProductDto>> getProductByStatus(String status) {
        if(checkStatus(status)) {
            List<Product> productList = repository.findByStatus(status);
            List<ProductDto> productDtoList = productList.stream().map(mapper::toDto).collect(Collectors.toList());
            return ResponseDto.<List<ProductDto>>builder()
                    .code(0)
                    .message("Ok")
                    .success(true)
                    .data(productDtoList)
                    .build();
        }
        return ResponseDto.<List<ProductDto>>builder()
                .code(-3)
                .message("Status Error. Status must be 3 type or null.Recommended,Discount Not available or null")
                .build();
    }

    @Override
    public ResponseDto<List<ProductDto>> getAll() {
        List<Product> productList = repository.findAll();
        List<ProductDto> productDtoList = productList.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseDto.<List<ProductDto>>builder()
                .code(0)
                .message("OK")
                .success(true)
                .data(productDtoList)
                .build();
    }

    @Override
    public ResponseDto<List<ProductDto>> pagination(Integer p, Integer s) {
        if((p == null || p < 0) && (s == null || s < 1) ) {
            return ResponseDto.<List<ProductDto>>builder()
                    .code(-3)
                    .message("Pagination Error")
                    .success(false)
                    .build();
        }
        PageRequest pageRequest = PageRequest.of(p,s);
        Page<Product> page = repository.findAllBy(pageRequest);
        List<ProductDto> productDtoPage = page.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseDto.<List<ProductDto>>builder()
                .code(0)
                .message("OK")
                .success(true)
                .data(productDtoPage)
                .build();
    }

    @Override
    public ResponseDto<ProductDto> getByName(String name) {
        Optional<Product> product = repository.findFirstByNameRu(name);
        if(product.isEmpty()) {
            return ResponseDto.<ProductDto>builder()
                    .code(-3)
                    .message("Not found")
                    .success(false)
                    .build();
        }
        return ResponseDto.<ProductDto>builder()
                .code(0)
                .message("OK")
                .success(true)
                .data(mapper.toDto(product.get()))
                .build();
    }

    @Override
    public Boolean orderProduct(ProductDto productDto) {
        try {
            Optional<Product> optional = repository.findById(productDto.getId());
            if(optional.isPresent() && !optional.get().getAmount().equals(0)) {
                Product product = optional.get();
                product.setAmount(product.getAmount() - 1);
                repository.save(product);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean checkStatus(String status) {
        if(status.equals("Recommended") || status.equals("Discount") || status.equals("Not available") || status.isEmpty()) {
            return true;
        }
        return false;
    }
}
