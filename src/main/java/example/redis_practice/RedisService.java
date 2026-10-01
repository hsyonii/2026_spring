package example.redis_practice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RedisService {

    // [1] 레디스 조작 객체 주입
    private final StringRedisTemplate redisTemplate;

    // [1] 레디스 List 우측에 데이터 추가
    public boolean addData(String data) {
        try {
            redisTemplate.opsForList().rightPush("dataListRedis", data);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // [2] 레디스 전체 데이터 목록 조회 (0 ~ -1)
    public List<String> getAllData() {
        try {
            List<String> list = redisTemplate.opsForList().range("dataListRedis", 0, -1);
            if (list == null) {
                return new ArrayList<>();
            }
            return list;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}