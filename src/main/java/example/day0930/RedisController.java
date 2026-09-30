package example.day0930;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RestController @RequestMapping ("/api/redis")
@RequiredArgsConstructor 
public class RedisController {
    // 1. Redis 조작 객체( 문자열 기반의 자료를 Redis에 CRUD 할수 있게 함. )
    private final StringRedisTemplate srt;

    @GetMapping ("/test1")
    public Map<String,Object> test1(){ // Map< 키타입, 값타입 > : 키 / 값으로 이루어진 한쌍의 데이터
                                            // Object : java의 최상위 타입, 모든 타입 데이터 담울 수 있다.
        // Redis에 자료 넣기
        srt.opsForValue().set("유재석","90");
        srt.opsForValue().set("강호동","100");
        srt.opsForValue().set("신동엽","70");

        // Redis에서 자료 조회하기( 반환 타입 : Set<String> )
        // .keys(" ") - ""안에 조회할 키 값 넣어준다.
        Set<String>keys = srt.keys("*"); // *는 모든 키 값 조회
        Map<String,Object> map = new HashMap<>();
        for( String key : keys ){
            String data = srt.opsForValue().get(key);
            map.put(key,data);
        }
        return map;
    }
    // Redis에 Dto 저장하기 ( 직렬화 : Dto -> String 타입 )
    private final ObjectMapper objectMapper = new ObjectMapper();
    @PostMapping ("/member")
    public boolean save(@RequestBody MemberDto memberDto) throws JsonProcessingException{
        String key = "member:" + memberDto.getMno(); // 저장 예) member: 3  
        String str = objectMapper.writeValueAsString(memberDto);
        srt.opsForValue().set(key, str);
        return true;
    }

    // 전체조회
    public List<MemberDto> findAll(){
        // 특정 패턴의 key 조회하기: member:* -> member로 시작하는 모든 키
        Set<String> keys = srt.keys("member:*");
        List<MemberDto> list = new ArrayList<>();
        for (String key: keys ){
            String value = srt.opsForValue().get(key);
            // 역직렬화 : 문자열 -> 객체( Dto )
            // objectMapper.readValue( 값, 타입명.class )
            MemberDto memberDto = objectMapper.readValue(value,MemberDto.class);
            list.add(memberDto);
        }
        return list;
    }

    // 개별조회
    @GetMapping ("/member/find")
    public MemberDto find(@RequestParam (name = "mno") Long mno )throws Exception{
        String findKey = "member:" + mno;
        String value = srt.opsForValue().get(findKey);
        if(value == null) return null;
        // objecMapper.readValue( 문자열 , 변환할타입명.class) : 문자열 -> 변환할타입 으로 변환
        MemberDto memberDto = objectMapper.readValue(value, MemberDto.class);
        return memberDto;
    }

    // 삭제
    @DeleteMapping ("/member")
    public boolean delete(@RequestParam (name = "mno") Long mno){
        String deleteKey = "member: "+mno;
        boolean result = srt.delete(deleteKey);
        return result;
    }

    // 수정
    @PutMapping ("/member")
    public boolean update(@RequestBody  MemberDto memberDto) throws JsonProcessingException{
        String updateKey = "member:"+memberDto.getMno();
        if(updateKey == null) return false;
        String value = objectMapper.writeValueAsString(memberDto); // 직렬화 ( dto -> String )
        srt.opsForValue().set(updateKey,value);
        return true;

    }
    
}
