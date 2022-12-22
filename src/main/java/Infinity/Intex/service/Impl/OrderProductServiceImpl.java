package Infinity.Intex.service.Impl;

import Infinity.Intex.dto.OrderProductDto;
import Infinity.Intex.dto.ResponseDto;
import Infinity.Intex.mapper.OrderProductMapper;
import Infinity.Intex.model.OrderProduct;
import Infinity.Intex.repository.OrderProductRepository;
import Infinity.Intex.service.OrderProductService;
import Infinity.Intex.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProductServiceImpl implements OrderProductService {
    private final OrderProductRepository repository;
    private final OrderProductMapper mapper;
    private final ProductService productService;

    @Override
    public ResponseDto<Void> add(OrderProductDto orderProductDto) {
        try {
            if(productService.orderProduct(orderProductDto.getProduct())) {
                repository.save(mapper.toEntity(orderProductDto));
                return ResponseDto.<Void>builder()
                        .code(0)
                        .message("OK")
                        .success(true)
                        .build();
            }
            return ResponseDto.<Void>builder()
                    .code(-2)
                    .message("Product not found")
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
    public ResponseDto<Void> update(OrderProductDto orderProductDto,Integer id) {
        try {
            if(repository.existsById(id)) {
                orderProductDto.setId(id);
                repository.save(mapper.toEntity(orderProductDto));
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
    public ResponseDto<OrderProductDto> getByName(String name) {
        Optional<OrderProduct> optional = repository.findFirstByFullName(name);
        if(optional.isEmpty()) {
            return ResponseDto.<OrderProductDto>builder()
                    .code(-3)
                    .message("Not found")
                    .success(false)
                    .build();
        }
        return ResponseDto.<OrderProductDto>builder()
                .code(0)
                .message("OK")
                .success(true)
                .data(mapper.toDto(optional.get()))
                .build();
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
    public ResponseDto<List<OrderProductDto>> getAll() {
        List<OrderProduct> orders = repository.findAll();
        List<OrderProductDto> orderProductDtos  = orders.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseDto.<List<OrderProductDto>>builder()
                .code(0)
                .message("OK")
                .success(true)
                .data(orderProductDtos)
                .build();
    }

    @Override
    public ResponseDto<Void> changeIsViewById(Integer id) {
        Optional<OrderProduct> optional = repository.findById(id);
        if(optional.isPresent()) {
            OrderProduct orderProduct = optional.get();
            orderProduct.setIsView(orderProduct.getIsView() ? false : true);
            repository.save(orderProduct);
            return ResponseDto.<Void>builder()
                    .code(0)
                    .message("OK")
                    .success(true)
                    .build();
        }
        return ResponseDto.<Void>builder()
                .code(-2)
                .message("Id not found in DB")
                .success(false)
                .build();
    }
}
