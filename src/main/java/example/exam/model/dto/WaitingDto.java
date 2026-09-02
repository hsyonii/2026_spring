package example.exam.model.dto;

//DTO 작성하기
public class WaitingDto {
    //1. 매개변수
    private int no;
    private String phone;
    private int headcount;

    //2. 기본생성자
    public WaitingDto() {}

    //3. 매개변수 생성자 : ( )안에 전달받은 데이터로 변수들(no/phone/headcount)을 초기화(값 덮어씌우기)
    public WaitingDto(int no, String phone, int headcount) {
        this.no = no;
        this.phone = phone;
        this.headcount = headcount;
    }

    //4. getter/setter
    //set + 매개변수명
    //get + 매개변수명 으로 해야함
    //getter : private 변수를 밖으로 꺼냄
    public int getNo() { return no; }
    //setter : 외부에서 값을 전달받아 내부 변수에 저장
    public void setNo(int no) { //int no : 매개변수로 받을 no
        this.no = no; //클래스 내부에 있는 private int no
    }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public int getHeadcount() { return headcount; }
    public void setHeadcount(int headcount) { this.headcount = headcount; }
}