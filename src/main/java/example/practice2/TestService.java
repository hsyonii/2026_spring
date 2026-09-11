package example.practice2;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestService {
    //리포지토리 불러오기
    private final TestRepository tr;

    //전체조회
    public List<TestEntity> findAll(){
        return tr.findAll(); //리포지토리객체.findAll(); : JPA 기본제공 전체조회 메소드
    }

    //저장
    public boolean save(TestEntity testEntity){
        TestEntity savedEntity = tr.save(testEntity);
        // 리포지토리객체.save( 저장할Entity ) : JPA 기본제공 저장 메소드, 저장된 하나의 새 Entity객체 반환!!
        if(savedEntity.getTno()>=1) // 해당 Entity의 PK값 가져와 저장 검증
            return true;
        else
            return false;
    }

    //삭제
    public boolean delete( Integer no ){
        tr.deleteById(no); // 리포지토리객체.deleteById(PK) : JPA 기본제공 삭제 메소드
        return true;
    }

    //수정
    @Transactional
    public boolean update(TestEntity testEntity){ // 매개변수는 수정할( 새로운 ) Entity이나, 기존( 수정 될 ) Entity와 PK는 동일하므로 수정 대상을 찾아갈 수 있다.
        // Optional<타입> 변수명 : null도 담을 수 있는 래퍼 클래스. 수정 될 Entity가 없는 Entity일 경우 예외 처리위해 사용!!
        // 리포지토리객체.findById(PK) : JPA 기본제공 메소드, PK값으로 Entity 찾아줌( 반환 타입 - Optional<~~Entity> )
        // 1. 동일한 PK 가진( 수정 될 ) Entity 찾기
        Optional<TestEntity> optional = tr.findById(testEntity.getTno());
        // 2. 찾은 Entity가 비어있지 않을 때( 존재할 때 )
        if(optional.isPresent()){ //객체.isPresent() : 객체가 null이면 false, 아니면 true
            // 2-1. 찾은 Entity 옮겨담고 값 덮어씌우기
            TestEntity oldEntity = optional.get();
            oldEntity.setTcontent(testEntity.getTcontent());
            // @transactional 을 붙이면 여기에서 oldEntity를 다시 tr( 리포지토리 객체 )에 저장하지 않아도 됨( 변경 감지 - 자동 반영 된다. )!!
            return true;
        }
        else
            return false;
    }
}
