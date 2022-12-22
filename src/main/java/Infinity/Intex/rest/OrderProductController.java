package Infinity.Intex.rest;

import Infinity.Intex.dto.OrderProductDto;
import Infinity.Intex.dto.ResponseDto;
import Infinity.Intex.service.OrderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/order")
@RestController
public class OrderProductController {
    private final OrderProductService service;
    @GetMapping
    public ResponseDto<List<OrderProductDto>> getAll() {
        return service.getAll();
    }

    @PostMapping()
    public ResponseDto<Void> add(@RequestBody @Valid OrderProductDto orderProductDto) {
        return service.add(orderProductDto);
    }

    @DeleteMapping("/{id}")
    public ResponseDto<Void> deleteById(@PathVariable Integer id) {
        return service.deleteById(id);
    }

    @PutMapping("/is-view/{id}")
    public ResponseDto<Void> changeIsViewById(@PathVariable Integer id) {
        return service.changeIsViewById(id);
    }

    @PutMapping("/{id}")
    public ResponseDto<Void> update(@RequestBody OrderProductDto orderProductDto,@PathVariable("id") Integer id) {
        return service.update(orderProductDto,id);
    }
    @GetMapping("/by-name")
    public ResponseDto<OrderProductDto> getByName(@RequestParam String name) {
        return service.getByName(name);
    }
}
