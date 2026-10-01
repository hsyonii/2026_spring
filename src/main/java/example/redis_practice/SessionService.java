package example.redis_practice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor 
public class SessionService {

    // [1] 데이터 추가 로직
    public List<String> addData(List<String> currentList, String data) {
        if (currentList == null) {
            currentList = new ArrayList<>();
        }
        currentList.add(data);
        return currentList;
    }

    // [2] 데이터 조회 로직 (null 안전 처리)
    public List<String> getAllData(List<String> currentList) {
        if (currentList == null) {
            return new ArrayList<>();
        }
        return currentList;
    }
}