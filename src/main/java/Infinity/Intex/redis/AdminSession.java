package Infinity.Intex.redis;

import Infinity.Intex.dto.AdminDto;
import Infinity.Intex.model.Admin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(timeToLive = 60 * 60 * 10)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminSession {
    private String id;
    private Admin admin;
}
