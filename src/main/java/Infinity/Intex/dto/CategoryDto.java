package Infinity.Intex.dto;

import Infinity.Intex.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * A DTO for the {@link Category} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    private Integer id;
    private String nameUz;
    @NotBlank(message = "Field must not be empty")
    private String nameRu;
    private Integer parentId;
    private List<ProductDto> productList;
}