package Infinity.Intex.dto;

import Infinity.Intex.model.Consultation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * A DTO for the {@link Consultation} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(value = {"createdAt"},ignoreUnknown = true)
public class ConsultationDto {
    private Integer id;
    @NotBlank(message = "Field must not be empty")
    private String fullName;
    @NotBlank(message = "Field must not be empty")
    private String phoneNumber;
    private Timestamp createdAt;
    private Boolean isView;
}