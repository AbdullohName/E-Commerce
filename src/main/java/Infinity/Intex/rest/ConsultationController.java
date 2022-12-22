package Infinity.Intex.rest;

import Infinity.Intex.dto.ConsultationDto;
import Infinity.Intex.dto.ResponseDto;
import Infinity.Intex.service.ConsultationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/consultation")
@RestController
public class ConsultationController {
    private final ConsultationService service;
    @GetMapping
    public ResponseDto<List<ConsultationDto>> getAll() {
        return service.getAll();
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
    public ResponseDto<Void> update(@RequestBody ConsultationDto consultationDto,@PathVariable("id") Integer id) {
        return service.update(consultationDto,id);
    }
    @GetMapping("/by-name")
    public ResponseDto<ConsultationDto> getByName(@RequestParam String name) {
        return service.getByName(name);
    }
    @PostMapping
    public ResponseDto<String> add(@RequestBody @Valid ConsultationDto consultationDto) {
        return service.add(consultationDto);
    }
}
