package example.practice5.model.dto;

import java.time.LocalDateTime;

import example.practice5.model.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder 
public class CommentDto {
    private Integer comment_id;
    private String author;
    private String password;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Integer board_id;

    public CommentEntity toEntity(){
        return CommentEntity.builder()
        .author(this.author)
        .password(this.password)
        .content(this.content)
        .build();
    }

    public static CommentDto from(CommentEntity commentEntity){
        return CommentDto.builder()
        .comment_id(commentEntity.getComment_id())
        .author(commentEntity.getAuthor())
        .password(commentEntity.getPassword())
        .content(commentEntity.getContent())
        .createdAt(commentEntity.getCreatedAt())
        .updatedAt(commentEntity.getUpdatedAt())
        .build();
    }
}
