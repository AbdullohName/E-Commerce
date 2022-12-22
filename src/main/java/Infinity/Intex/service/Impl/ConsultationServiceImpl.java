package Infinity.Intex.service.Impl;

import Infinity.Intex.dto.ConsultationDto;
import Infinity.Intex.dto.OrderProductDto;
import Infinity.Intex.dto.ResponseDto;
import Infinity.Intex.mapper.ConsultationMapper;
import Infinity.Intex.model.Consultation;
import Infinity.Intex.model.OrderProduct;
import Infinity.Intex.repository.ConsultationRepository;
import Infinity.Intex.service.ConsultationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsultationServiceImpl implements ConsultationService {
    private final ConsultationRepository repository;
    private final ConsultationMapper mapper;
    @Override
    public ResponseDto<List<ConsultationDto>> getAll() {
        List<Consultation> consultations = repository.findAll();
        List<ConsultationDto> consultationDtoList = consultations.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseDto.<List<ConsultationDto>>builder()
                .code(0)
                .message("OK")
                .success(true)
                .data(consultationDtoList)
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
                .message("Id not found in DB")
                .success(false)
                .build();
    }

    @Override
    public ResponseDto<Void> changeIsViewById(Integer id) {
        Optional<Consultation> optional = repository.findById(id);
        if(optional.isPresent()) {
            Consultation consultation = optional.get();
            consultation.setIsView(consultation.getIsView() != true ? true : false);
            repository.save(consultation);
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

    @Override
    public ResponseDto<Void> update(ConsultationDto consultationDto,Integer id) {
        if(repository.existsById(id)) {
            consultationDto.setId(id);
            repository.save(mapper.toEntity(consultationDto));
            return ResponseDto.<Void>builder()
                    .code(0)
                    .message("OK")
                    .success(true)
                    .build();
        }
        return ResponseDto.<Void>builder()
                .code(-2)
                .message("Not found in Db")
                .success(false)
                .build();
    }

    @Override
    public ResponseDto<ConsultationDto> getByName(String name) {
        Optional<Consultation> optional = repository.findFirstByFullName(name);
        if(optional.isEmpty()) {
            return ResponseDto.<ConsultationDto>builder()
                    .code(-3)
                    .message("Not found")
                    .success(false)
                    .build();
        }
        return ResponseDto.<ConsultationDto>builder()
                .code(0)
                .message("OK")
                .success(true)
                .data(mapper.toDto(optional.get()))
                .build();
    }

    @Override
    public ResponseDto<String> add(ConsultationDto consultationDto) {
        try {
            consultationDto.setIsView(false);
            repository.save(mapper.toEntity(consultationDto));
            return ResponseDto.<String>builder()
                    .code(0)
                    .message("OK")
                    .success(true)
                    .data("Successfully Saved")
                    .build();
        } catch (Exception e) {
            return ResponseDto.<String>builder()
                    .code(-3)
                    .success(false)
                    .message(e.getMessage())
                    .build();
        }
    }
}
