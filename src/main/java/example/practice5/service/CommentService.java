package example.practice5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.practice5.model.dto.CommentDto;
import example.practice5.model.entity.CommentEntity;
import example.practice5.model.repository.BoardRepository;
import example.practice5.model.repository.CommentRepository;

@Service 
public class CommentService {
    @Autowired private CommentRepository cr;
    @Autowired private BoardRepository br;

    public boolean commentSave(CommentDto commentdto){
        CommentEntity ce = commentdto.toEntity();
        CommentEntity saved = cr.save(ce);
        //br 에 ce 추가 ??
        if(saved.getComment_id()>=1){
            return  true;
        }
        return false;
    }

    public boolean commentDelete(Integer comment_id, String password){
        CommentEntity ce = cr.findById(comment_id).orElse(null);
        if(ce!=null){
            if(ce.getPassword().equals(password)){
                cr.deleteById(comment_id);
                return true;
            }
        }
        return false;
    }
}
