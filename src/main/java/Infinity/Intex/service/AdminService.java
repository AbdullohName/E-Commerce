package Infinity.Intex.service;

import Infinity.Intex.dto.AdminDto;
import Infinity.Intex.dto.ResponseDto;

import javax.servlet.http.HttpServletRequest;


public interface AdminService {
    ResponseDto<String> generateJWT(AdminDto adminDto, HttpServletRequest request);
    ResponseDto<Void> addAdmin(AdminDto adminDto);

    ResponseDto<Void> update(AdminDto adminDto,Integer id);
    ResponseDto<Integer> deleteByUsername(String name);
}
