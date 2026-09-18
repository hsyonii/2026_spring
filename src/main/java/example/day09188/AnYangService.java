package example.day09188;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.stereotype.Service;

@Service 
public class AnYangService {
    // application.properties 에 api.public-data.service-key 로 할당된 api 키값  정의
    @Value ("${api.public-data.service-key}")
    private  String myKey; // @Value 값을 serviceKey 에 주입

    WebClient wc = WebClient.builder().build();

    public Map<String,Object> findAll(){
        String url = "https://api.odcloud.kr/api/3045179/v1/uddi:8e74c407-37bd-453b-9d16-bef24ca26490";
        url += "?page="+1;
        url += "&perPage="+10;
        url += "&serviceKey="+ myKey;
        
        Map<String,Object> re = wc.get()
            .uri(url)
            .retrieve()
            .bodyToMono(Map.class)
            .block();

        return re;
    }
}
