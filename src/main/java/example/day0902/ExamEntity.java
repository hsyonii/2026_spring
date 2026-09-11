package example.day0902;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.GenerationType;

@Entity // 빈( 엔티티 객체 ) 등록
@Table( name = "exam" ) // @Table( name = "테이블명" ) : 매핑(연결)할 DB테이블명 정의 , 생략시 클래스명으로 (ex - ExamEntity) 자동매핑
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class ExamEntity {
    // 엔티티는 무조건 1개이상의 PK 를 갖는다.
    @Id // PK 지정하는 어노테이션
    @GeneratedValue( strategy = GenerationType.IDENTITY ) // auto increment 지원
    private Integer eno;
    private String ename;
}
