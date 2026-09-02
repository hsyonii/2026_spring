package example.day0825;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;

//1. 해당 컨트롤러에게 HTTP적용하기 <- 서블릿 필요
//2. 서블릿에게 상속받기 : extends HttpServlet
//3. 물려받은 기능 ( init , service , destroy ) 재정의 (오버라이딩)
//4. HTTP doXXX 메소드 오버라이딩 하여 기능 구현 -> 컨트롤러 역할
//5. 해당 컨트롤러에 HTTP 주소 등록 , @webServlet ( "/주소정의 ") 
public class BoardController extends HttpServlet{
    // init : 서블릿이 최초 실행될때 딱 1번 실행되는 메소드
    @Override
    public void init() throws ServletException {
        // TODO Auto-generated method stub
        super.init();
    }
    // service : 서블릿 생성 후 요청마다 (스레드풀 스레드 할당받아)실행되는 메소드
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.service(req, res);
    }
    // destroy : 서블릿이 사라질때 (서버 종료 시) 1번 실행되는 메소드
    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        super.destroy();
    }
}
