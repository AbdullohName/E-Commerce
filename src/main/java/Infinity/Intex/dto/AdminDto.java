package Infinity.Intex.dto;

import Infinity.Intex.model.Admin;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

/**
 * A DTO for the {@link Admin} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminDto {
    private Integer id;
    private String fullName;
    @NotBlank(message = "fullName must not be empty")
    private String username;
    @NotBlank(message = "fullName must not be empty")
    private String password;
    private List<ProductDto> productList;
}