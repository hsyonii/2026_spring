package example.day0930;

import java.net.http.HttpHeaders;
import java.time.Duration;

import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/member")
@RequiredArgsConstructor 
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class MemberController {
    private final MemberService memberService;
    private final JwtUtil ju;
    // [1] 회원가입
    @PostMapping("/signup")
    public boolean signup( @RequestBody MemberDto memberDto ){
        return memberService.signup( memberDto );
    }

    // [2] 로그인 + 쿠키변경( 회원 식별(번호) 쿠키에 담아 클라이언트에 전송 )
    @PostMapping("/login")
    public MemberDto login( @RequestBody MemberDto memberDto , HttpServletResponse response ){
        // 1. 서비스에게 인증 / 로그인 확인 ( 기존 유지 )
        MemberDto re = memberService.login(memberDto);
        // 로그인 실패시
        if( re == null ) return null;
        // 로그인 성공시 쿠키 생성/발급 - 민감한 정보( 회원번호 외 )는 쿠키에 넣지 않음.
        // ResponseCookie cookie = ResponseCookie.from( "쿠키명", "쿠키값" )
        // **정수 -> 문자 타입 변환하기
        // (1) 정수 + ""  (2) String.valueOf(정수)
        ResponseCookie cookie = ResponseCookie.from("login_member",re.getMno()+"")
            .path("/") // 쿠키 사용할 경로, "/" : 도메인 전체
            .maxAge(Duration.ofDays(1)) // Duration.of~~ (n) : 쿠키의 유효기간 설정
            .httpOnly(true)
            .secure(false) // https에서만 사용되도록하는 설정. 개발단계에서는 false로 둔다.
            .sameSite("Lax") // CSRF 공격 방어
            .build(); // 쿠기값은 String이어야 함.

        // 3. 응답 헤더에  쿠키 등록
        response.setHeader(org.springframework.http.HttpHeaders.SET_COOKIE, cookie.toString());
        return re;
    }

    // [3] 내정보조회 + 쿠키 ( 이미 로그인된 회원이 내정보 요청 ) 
    @GetMapping("/me")
    // @CookieValue ( value = "쿠키명")
    public MemberDto getMyInfo(
        @CookieValue (value = "login_member", required = false) String loginMno ){
            if( loginMno == null ) return null;
            return memberService.getMyInfo(Long.parseLong(loginMno));
    }

    // [4] 로그아웃 + 쿠키
    @PostMapping ("/logout")
    public boolean logout( HttpServletResponse response ){
        // 1. 삭제할 쿠키명과 동일한 이름으로 maxAge(0)하여 재발급
        ResponseCookie cookie = ResponseCookie.from("login_member", "")
            .path("/") // 모든곳에서 로그아웃 가능하도록 함.
            .maxAge(0) // 바로 삭제
            .httpOnly(true).secure(false)    
            .build();
        response.setHeader( org.springframework.http.HttpHeaders.SET_COOKIE, cookie.toString());
        return true;
    }
}
/*

    @GetMapping("")
    public String test( HttpServletRequest request ){
        //1) HttpServletRequest: HTTP 요청이 들어오면 요청 정보가 담겨 있는 객체
        System.out.println( request.getRemoteAddr() ); // 요청한 클라이언트의 IP
        System.out.println( request.getHeader("User-Agent")); // 요청한 클라이언트 브라우저 정보
        System.out.println( request.getSession() ); // 요청한 클라이언트의 세션객체 정보
        // 2) 새션객체란? 톰캣 서버내 브라우저 마다 독립적인 저장소
        // 주로 : *로그인성공정보*, 인증번호, 비회원제장바구니 등등 일시적인 휘발성 메모리
        HttpSession session = request.getSession(); // 세션객체내 여러개 정보 저장 가능
        System.out.println( session.getId() ); // 세션 식별번호 
        System.out.println( session.getCreationTime() ); // 세션 생성시간
        System.out.println( session.getLastAccessedTime() ); // 세션 마지막접근 시간
        System.out.println( session.getMaxInactiveInterval() ); // 세션 생명주기( 기본값30분 )
        // 3) 세션 정보 저장=로그인/호출=마이페이지/삭제=로그아웃
        session.setAttribute( "data", "사과"); // map(key,value)구조
        // data 이름(key) 으로 사과(data) 저장 , 주의할점: value 타입은 Object 이라서 타입변환 필요
        System.out.println( session.getAttribute("data")); // key 이용한 value 호출 
        session.invalidate(); // 세션 초기화
        return session.getId();
    }

*/
