package example.practice5.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import example.practice5.model.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder 
public class BoardDto {
    private Integer board_id;
    private String author;
    private String password;
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder.Default
    List<CommentDto> commentDtos = new ArrayList<>();

    // 반환타입 생각안나서 살짝 봤어요...
    public  BoardEntity toEntity(){
        return BoardEntity.builder()
            .author(this.author)
            .password(this.password)
            .content(this.content)
            .build();
    }

    public static BoardDto from(BoardEntity boardEntity){
        return BoardDto.builder()
        .board_id(boardEntity.getBoard_id())
        .author(boardEntity.getAuthor())
        .password(boardEntity.getPassword())
        .content(boardEntity.getContent())
        .createdAt(boardEntity.getCreatedAt())
        .updatedAt(boardEntity.getUpdatedAt())
        .build();
    }
}
