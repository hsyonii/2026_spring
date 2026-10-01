package example.redis_practice;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service @RequiredArgsConstructor 
public class CookieService {

    // [1] 기존 쿠키 문자열 뒤에 새 data를 콤마(,)로 이어붙임
    public String appendData(String currentCookieValue, String newData) {
        if (currentCookieValue == null || currentCookieValue.trim().isEmpty()) {
            return newData;
        }
        return currentCookieValue + "," + newData;
    }

    // [2] 콤마(,)로 묶인 쿠키 문자열을 List<String> 형태로 파싱
    public List<String> parseToList(String cookieValue) {
        if (cookieValue == null || cookieValue.trim().isEmpty()) {
            return new ArrayList<>();
        }
        String[] items = cookieValue.split(",");
        return new ArrayList<>(Arrays.asList(items));
    }
}