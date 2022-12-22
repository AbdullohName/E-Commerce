package Infinity.Intex.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto <T> {
    private Integer code;
    private String message;
    private Boolean success;
    private T data;
}
