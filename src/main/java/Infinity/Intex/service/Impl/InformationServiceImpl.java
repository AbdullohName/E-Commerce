package Infinity.Intex.service.Impl;

import Infinity.Intex.dto.InformationDto;
import Infinity.Intex.dto.ResponseDto;
import Infinity.Intex.mapper.InformationMapper;
import Infinity.Intex.model.Information;
import Infinity.Intex.repository.InformationRepository;
import Infinity.Intex.service.InformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class InformationServiceImpl implements InformationService {
    private final InformationRepository repository;
    private final InformationMapper mapper;

    @Override
    public ResponseDto<InformationDto> get() {
         Optional<Information> optional = repository.findById(1);
         if(optional.isPresent()) {
             return ResponseDto.<InformationDto>builder()
                     .code(0)
                     .message("OK")
                     .success(true)
                     .data(mapper.toDto(optional.get()))
                     .build();
         }
         return ResponseDto.<InformationDto>builder()
                 .code(-2)
                 .message("DB Error")
                 .success(false)
                 .build();
    }

    @Override
    public ResponseDto<Void> update(InformationDto informationDto,Integer id) {
        if(repository.existsById(id)) {
            informationDto.setId(id);
            repository.save(mapper.toEntity(informationDto));
            return ResponseDto.<Void>builder()
                    .code(0)
                    .message("OK")
                    .success(true)
                    .build();
        }
        return ResponseDto.<Void>builder()
                .code(-2)
                .message("Not found In DB")
                .success(false)
                .build();
    }
}
