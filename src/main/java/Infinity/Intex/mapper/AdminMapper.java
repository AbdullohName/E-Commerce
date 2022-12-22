package Infinity.Intex.mapper;

import Infinity.Intex.dto.AdminDto;
import Infinity.Intex.model.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    Admin toEntity(AdminDto adminDto);
    AdminDto toDto(Admin admin);
}
