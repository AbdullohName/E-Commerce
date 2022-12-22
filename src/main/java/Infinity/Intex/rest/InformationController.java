package Infinity.Intex.rest;

import Infinity.Intex.dto.InformationDto;
import Infinity.Intex.dto.ResponseDto;
import Infinity.Intex.service.InformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/info")
@RestController
public class InformationController {
    private final InformationService service;
    @GetMapping
    public ResponseDto<InformationDto> get() {
        return service.get();
    }
    @PutMapping("/{id}")
    public ResponseDto<Void> update(@RequestBody InformationDto informationDto,@PathVariable("id") Integer id) {
        return service.update(informationDto,id);
    }
}
