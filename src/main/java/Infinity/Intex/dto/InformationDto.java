package Infinity.Intex.dto;

import Infinity.Intex.model.Information;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link Information} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformationDto {
    private Integer id;
    private String phoneNumber;
    private String address;
    private String workTime;
    private String telegram;
    private String instagram;
}