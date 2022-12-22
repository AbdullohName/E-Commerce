package Infinity.Intex.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminSessionRedisRepository extends CrudRepository<AdminSession,String> {
}
