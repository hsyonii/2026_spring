package example.practice5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.practice5.model.dto.CommentDto;
import example.practice5.service.CommentService;

@RestController 
@RequestMapping ("/api/board/comment")
public class CommentController {
    @Autowired private CommentService cs;

    @PostMapping ("")
    private boolean commentSave(@RequestBody CommentDto commentDto){
        return cs.commentSave(commentDto);
    }

    @DeleteMapping ("")
    private boolean commentDelte(
        @RequestParam Integer comment_id,
        @RequestParam String password
    ){
        return cs.commentDelete(comment_id, password);
    }
}
