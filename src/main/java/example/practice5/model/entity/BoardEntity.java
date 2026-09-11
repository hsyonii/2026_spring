package example.practice5.model.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity @Table (name = "board")
@Data  @NoArgsConstructor @AllArgsConstructor @Builder 
public class BoardEntity extends BaseTime{
    @Id 
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer board_id;
    private String author;
    private String password;
    private String content;

    @OneToMany (mappedBy = "boardEntity", cascade = CascadeType.ALL)
    @ToString .Exclude
    @Builder .Default
    List<CommentEntity> commentEntities = new ArrayList<>();

}
