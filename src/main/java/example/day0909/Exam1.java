package example.day0909;

public class Exam1 {
    public static void main(String[] args) {
       // 1. 인스턴스 생성하여 메소드 호출하기
       TestService ts = new TestService();
       int re = ts.plus(1, 2); // re = 3 
    }
}

class TestService{
    int plus(int x , int y){
        return x+y;
    }

}

class Student{
    String name;
    void 밥먹기(){
        System.out.println(this.name+"밥 먹는다.");
    }
}
