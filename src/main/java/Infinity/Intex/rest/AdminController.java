package Infinity.Intex.rest;

import Infinity.Intex.dto.AdminDto;
import Infinity.Intex.dto.ResponseDto;
import Infinity.Intex.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService service;
    @PostMapping("/token")
    public ResponseDto<String> generateJWT(@RequestBody @Valid AdminDto adminDto, HttpServletRequest request) {
        return service.generateJWT(adminDto,request);
    }

    @PostMapping()
    public ResponseDto<Void> addAdmin(@RequestBody @Valid AdminDto adminDto) {
        return service.addAdmin(adminDto);
    }
    @PutMapping("/{id}")
    public ResponseDto<Void> update(@RequestBody AdminDto adminDto,@PathVariable("id") Integer id) {
        return service.update(adminDto,id);
    }
    @DeleteMapping
    public ResponseDto<Integer> deleteByUsername(@RequestParam String name) {
        return service.deleteByUsername(name);
    }
}
