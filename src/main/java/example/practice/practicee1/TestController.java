
package example.practice.practicee1;

import java.util.ArrayList;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController // Controller 위에 붙여준다.
@RequestMapping("/test") // 공통 URL 정의 ( ex - "/test/save" , "/test/findall"이면 "/test"넣어줌. )
public class TestController {

    private ArrayList<TestDto> td = new ArrayList<>();
    public TestController(){
    TestDto sample1 = new TestDto(1,"안녕하세요","유재석");
    TestDto sample2 = new TestDto(2,"만반잘부","황소연");
    
    td.add(sample1);
    td.add(sample2);
    }
    // 1. 등록
    @PostMapping("/save")
    public boolean testWrite(@RequestBody TestDto testDto) {
        return td.add(testDto); // 임의로 true 설정
    }

    // 2. 전체 조회
    @GetMapping("/findall")
    public ArrayList<TestDto> testPrint() {
        return td; 
    }

    // 2-1. 개별 조회
    @GetMapping("/find")
    public TestDto testDetail(int no){
        return td.get(no-1);
    }

    // 3. 개별 삭제
    @DeleteMapping("/{no}") // http://localhost:8080/test/1
    public boolean testDelete(@PathVariable ( name = "no") int no ) {
        return true;
    }

    // 4. 개별 수정
    @DeleteMapping("/update")
    public boolean testUpdate(@RequestBody TestDto testDto) {
        return true;
    }
}

@Data // getter, setter, toString 자동 작성
@AllArgsConstructor @NoArgsConstructor @Builder
class TestDto{
    private int no;
    private String content;
    private String writer;
}
