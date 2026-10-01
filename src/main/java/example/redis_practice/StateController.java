package example.redis_practice;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StateController {

    private final SessionService sessionService;
    private final CookieService cookieService;
    private final RedisService redisService;

    // --- 1. 세션 ---
    @GetMapping("/session/add")
    public String addSession(@RequestParam("data") String data, HttpSession session) {
        @SuppressWarnings("unchecked")
        List<String> currentList = (List<String>) session.getAttribute("dataList");
        List<String> updatedList = sessionService.addData(currentList, data);
        session.setAttribute("dataList", updatedList);
        return "세션저장성공";
    }

    @GetMapping("/session/all")
    public List<String> getAllSession(HttpSession session) {
        @SuppressWarnings("unchecked")
        List<String> currentList = (List<String>) session.getAttribute("dataList");
        return sessionService.getAllData(currentList);
    }

    // --- 2. 쿠키 ---
    @GetMapping("/cookie/add")
    public String addCookie(@RequestParam("data") String data,
                            @CookieValue(value = "dataListCookie", required = false) String rawCookie,
                            HttpServletResponse response) {
        try {
            String currentVal = (rawCookie != null) ? URLDecoder.decode(rawCookie, StandardCharsets.UTF_8) : "";
            String updatedVal = cookieService.appendData(currentVal, data);

            ResponseCookie cookie = ResponseCookie.from("dataListCookie", URLEncoder.encode(updatedVal, StandardCharsets.UTF_8))
                    .path("/")
                    .maxAge(60 * 60 * 24)
                    .build();

            response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
            return "쿠키저장성공";
        } catch (Exception e) {
            return "쿠키저장실패";
        }
    }

    @GetMapping("/cookie/all")
    public List<String> getAllCookie(@CookieValue(value = "dataListCookie", required = false) String rawCookie) {
        if (rawCookie == null) return List.of();
        String decodedVal = URLDecoder.decode(rawCookie, StandardCharsets.UTF_8);
        return cookieService.parseToList(decodedVal);
    }

    // --- 3. 레디스 ---
    @GetMapping("/redis/add")
    public String addRedis(@RequestParam("data") String data) {
        boolean success = redisService.addData(data);
        return success ? "레디스저장성공" : "레디스저장실패";
    }

    @GetMapping("/redis/all")
    public List<String> getAllRedis() {
        return redisService.getAllData();
    }
}