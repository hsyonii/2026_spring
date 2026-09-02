package example.day0831;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class Exam2 {
    public static void main(String[] args) {
        // 1. @NoArgsConstructor
        Student s1= new Student();
        // 2. @AllArgsContstructor
        Student s2= new Student("유재석",100,90);
        // 3. @Setter
        s1.setKorean(100);
        // 4. @Getter
        System.out.println(s2.getKorean());
        // 5. @ToString
        System.out.println(s2.toString());
        // 6. @Build 패턴 이용한 객체 생성
        // - 장점 : 순서 무관, 선택적 대입, 유연한 객체 생성
        Student s3 = Student.builder() //빌더패턴 시작
            .korean(100)
            .name("강호동")
            .math(99)
            .build(); // 빌더 패턴 끝
        // -> new 생성자명(); vs 클래스명.builder().build()
    }
}

@NoArgsConstructor // : 매개변수 없는 생성자 ( 자동 )생성 
@AllArgsConstructor // : 전체 매개변수 있는 생성자 ( 자동 )생성
// @RequiredArgsConstructor // : final 멤버변수의 생성자 ( 자동 )생성
@Getter @Setter @ToString // : private 멤버변수의 getter, setter, toString 머소드 ( 자동 ) 생성
@Data // : getter, setter, ReqiredArgsConstructor, toString 묶음
@Builder // : 빌터 패턴 지원 
class Student{
    // 멤버변수
    private String name;
    private int korean; // 국어 점수
    private int math; // 수학 점수
    
    // 생성자 , getter&setter -> 롬복 어노테이션으로 대체
}
