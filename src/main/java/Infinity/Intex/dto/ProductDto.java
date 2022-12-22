package Infinity.Intex.dto;

import Infinity.Intex.model.Image;
import Infinity.Intex.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * A DTO for the {@link Product} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(value = {"sellerId"})
public class ProductDto {
    private Integer id;
    private String nameUz;
    @NotBlank(message = "Field must not be empty")
    private String nameRu;
    @Min(value = 100)
    @NotNull
    private Double oldPrice;
    @NotNull
    @Min(value = 0)
    private Double amount;
    @NotNull
    private Double currentPrice;
    @NotNull(message = "Category must not be empty")
    private Integer categoryId;
    private Integer sellerId;
    @NotBlank(message = "Field must not be empty")
    private String frameRu;
    private String frameUz;
    @NotNull(message = "Field must not be empty")
    private Float size;
    @NotNull(message = "Field must not be empty")
    @Min(value = 5)
    private Float depth;
    private String status;
    private String equipmentRu;
    private String equipmentUz;
    private Image image;
}