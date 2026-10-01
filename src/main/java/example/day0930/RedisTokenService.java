package example.day0930;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.time.Duration;
import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor  
public class RedisTokenService {
    //레디스 객체 주입
    private  final StringRedisTemplate srt;

    // 1. Refresh 토큰 레디스에 저장
    public void setRefreshToken(Long mno, String token ){
        // key - RT: 회원번호 , // value - Refresh토큰 , // 만료기간 - Duration.of~~( n )
        srt.opsForValue().set("RT:"+mno, token , Duration.ofDays(7)); // Duration.ofDays(7) : 만료기간 7일 
    }
    // 2. Refresh 토큰 레디스에서 조회
    public String getRefreshToken(Long mno){
        return srt.opsForValue().get("RT:"+mno);
    }
    
    // 3. Refresh 토큰 레디스에서 삭제
    public boolean deleteRefreshToken(Long mno){
        return srt.delete("RT:"+mno);
    }
}
