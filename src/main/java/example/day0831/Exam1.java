package example.day0831;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

public class Exam1 {
    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        subClass.method1();
        subClass.method2();

        // 1. 리플렉션 : (실행 중)클래스의 정보 반환
        Class<TestClass> class2 = TestClass.class; //클래스명.class : 클래스 자체의 정보 반환 ( 어노테이션, 메서드, 변수... )
        try{
            // 2. 특정 메소드 반환
            Method method = class2.getMethod("method3");
            // 3. 특정 메소드( method3 )의 어노테이션 확인
            MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
            // 4. 특정 어노테이션( MyAnnotation )의 속성 확인
            System.out.println(annotation.value());
            System.out.println(annotation.data());
            // 5. 동적 로딩
            TestClass testClass = class2.getDeclaredConstructor().newInstance();
            method.invoke(testClass);
        }
        cath(Exception e){
            System.out.println(e);
        }

    }
}
// 상위 클래스
class SuperClass {
    void method1(){}
}

// **@interface 어노테이션명{} : 사용자 정의 어노테이션**
// 1. 생명주기 , 2. 사용처 지정해야 함.
// @Retention ( RetentionPolicy.~~ ) : 해당 어노테이션 생명주기 
@Retention ( RetentionPolicy.RUNTIME ) // 실행 중에도 어노테이션 참조 가능
// @Target( ElementType.~~ ) : 해당 어노테이션 사용처
@Target(ElementType.METHOD) // 메소드 : 아래 어노테이션( MyAnnotation )은 메소드 위에만 붙일 수 있음.
@interface MyAnnotation{
    // 일반 메소드 : 반환값타입 메소드명(){}
    // 추상 메소드 : 반환값타입 메소드명(); -> {실행문} 없음
    String value(); // 추상 메소드
    // 메소드에 어노테이션 주입 시 기본값( default ) 지정  
    int data() default 1;
}

// ( 메소드에 )사용어노테이션 주입 / 사용
// 사용코드 윗줄에 @어노테이션명 or @어노테이션명( 속성 = 값 )
class TestClass{
    @MyAnnotation(value = "안녕하세요", data = 10) //@어노테이션명( )
    public void method3(){}
    @MyAnnotation(value = "안녕안녕") // data = 1 (default)
    public void method4(){}
 }


// 하위 클래스
// 기본 어노테이션
class SubClass extends SuperClass{
    //상위클래스의 메소드를 오버라이딩
    @Override // : 재정의
    void method1(){
        //super.부모클래스메소드명 : 부모클래스메소드(오버라이딩 전) 실행
        super.method1();
    }
    @Deprecated // : 더이상 사용안함 
    void method2(){}
}
