package example.practice2;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "test" ) // @Table( name = "테이블명" ) : 매핑(연결)할 DB테이블명 넣어준다.
@Data // getter, setter, toStirng(), ResquiredArgsContructor 포함
@AllArgsConstructor @NoArgsConstructor @Builder
public class TestEntity {
    	@Id // PK 지정하는 어노테이션
    	@GeneratedValue( strategy = GenerationType.IDENTITY ) // auto increment 지원
    	private Integer tno; // int 대신 integer 사용.
    	private String tcontent;
        private String twriter;
}
