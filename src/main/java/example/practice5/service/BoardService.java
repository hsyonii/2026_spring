package example.practice5.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.practice5.model.dto.BoardDto;
import example.practice5.model.dto.CommentDto;
import example.practice5.model.entity.BoardEntity;
import example.practice5.model.repository.BoardRepository;

@Service 
public class BoardService {
    @Autowired private BoardRepository br;

    public List<BoardDto> boardFindAll(){
        List<BoardEntity> beList = br.findAll();
        List<BoardDto> bdList = new ArrayList<>();
        beList.forEach((boardEntity)->{
           BoardDto bd = BoardDto.from(boardEntity);
           boardEntity.getCommentEntities().forEach((commentEntity)->{
            CommentDto cd = CommentDto.from(commentEntity);
            bd.getCommentDtos().add(cd);
            bdList.add(bd);
           });
        });
        return bdList;
    }

    public boolean boardDelte(Integer board_id, String password){
        BoardEntity be = br.findById(board_id).orElse(null);
        if(be!=null){
            if(be.getPassword().equals(password)){
                br.deleteById(board_id);
                return true;
            }
        }
        return false;
    }

    public boolean boardSave(BoardDto boardDto){
        BoardEntity be = boardDto.toEntity();
        BoardEntity saved = br.save(be);
        if(saved.getBoard_id() >= 1){
            return true;
        }
        return false;
    }
}
