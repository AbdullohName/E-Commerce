package Infinity.Intex.service.Impl;

import Infinity.Intex.dto.AdminDto;
import Infinity.Intex.dto.ResponseDto;
import Infinity.Intex.mapper.AdminMapper;
import Infinity.Intex.model.Admin;
import Infinity.Intex.redis.AdminSession;
import Infinity.Intex.redis.AdminSessionRedisRepository;
import Infinity.Intex.repository.AdminRepository;
import Infinity.Intex.security.jwt.JwtUtil;
import Infinity.Intex.service.AdminService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {
    private final AdminRepository repository;
    private final AdminSessionRedisRepository sessionRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminMapper mapper;
    private final JwtUtil jwtUtil;
    @Override
    public ResponseDto<String> generateJWT(AdminDto adminDto, HttpServletRequest request) {
        Admin admin = repository.findByUsername(adminDto.getUsername()).orElseThrow(
                () ->
                        new UsernameNotFoundException(String.format("User with username %s not found",adminDto.getUsername())));

                if(!passwordEncoder.matches(adminDto.getPassword(),admin.getPassword())) {
                    throw new BadCredentialsException("Password is incorrect!");
                }

                try {
                    AdminSession adminSession = new AdminSession(sysGuid(),admin);
                    sessionRepository.save(adminSession);
                    String token = jwtUtil.generateToken(adminSession.getId());
                    return ResponseDto.<String>builder()
                            .code(0)
                            .message("OK")
                            .success(true)
                            .data(token)
                            .build();
                } catch (Exception e) {
                    return ResponseDto.<String>builder()
                            .code(-1)
                            .success(true)
                            .message(e.getMessage())
                            .build();
                }
    }

    @Override
    public ResponseDto<Void> addAdmin(AdminDto adminDto) {
        try {
            Admin admin = mapper.toEntity(adminDto);
            admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
            repository.save(admin);
            return ResponseDto.<Void>builder()
                    .code(0)
                    .message("OK")
                    .success(true)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseDto.<Void>builder()
                    .code(-2)
                    .success(false)
                    .message("Error while adding new user to DB")
                    .build();
        }

    }

    @Override
    public ResponseDto<Void> update(AdminDto adminDto,Integer id) {
        if(repository.existsById(id)) {
            adminDto.setId(id);
            repository.save(mapper.toEntity(adminDto));
            return ResponseDto.<Void>builder()
                    .code(0)
                    .message("OK")
                    .success(true)
                    .build();
        }
        return ResponseDto.<Void>builder()
                .code(-2)
                .message("Admin not found")
                .success(false)
                .build();
    }

    @Override
    @Transactional
    public ResponseDto<Integer> deleteByUsername(String name) {
        Optional<Admin> optional = repository.findByUsername(name);
        if(optional.isEmpty()) {
            return ResponseDto.<Integer>builder()
                    .code(-3)
                    .message("Not found")
                    .success(false)
                    .build();
        }
        return ResponseDto.<Integer>builder()
                .code(0)
                .message("OK")
                .success(true)
                .data(repository.deleteByUsername(name))
                .build();
    }

    private String sysGuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
