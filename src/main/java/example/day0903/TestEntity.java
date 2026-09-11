package example.day0903;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
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
@Table(name = "test")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class TestEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer no;
    @Column(name = "name", nullable = false , length = 100 , unique = true) // 제약조건 설정 가능
    // name = "컬럼명" : 컬럼명 지정 , nullable = false : NOT NULL
    // length : VARCHAR , unique : UNIQUE ( 중복 불가 )
    private String name;
    private String descri;
    private Integer price;
    public LocalDateTime getCreateDate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCreateDate'");
    }
}
