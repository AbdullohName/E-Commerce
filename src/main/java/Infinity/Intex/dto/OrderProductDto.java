package Infinity.Intex.dto;

import Infinity.Intex.model.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * A DTO for the {@link OrderProduct} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProductDto {
    private Integer id;
    @NotBlank(message = "Field must not be empty")
    private String fullName;
    @NotBlank(message = "Field must not be empty")
    private String phoneNumber;
    @NotBlank(message = "Field must not be empty")
    private String address;
    @CreationTimestamp
    private Timestamp orderDate;
    @Column(columnDefinition = "boolean default false")
    private Boolean isView;
    private ProductDto product;
}