package example.practice5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.practice5.model.dto.BoardDto;
import example.practice5.service.BoardService;

@RestController 
@RequestMapping("/api/board")
public class BoardController {
    @Autowired private BoardService bs;


    @GetMapping ("")
    private List<BoardDto> boardFindAll(){
        return bs.boardFindAll(); //service에서 오류 있음
    }

    @PostMapping ("")
    private boolean boardSave(@RequestBody BoardDto boardDto){
        return bs.boardSave(boardDto);
    }

    @DeleteMapping("")
    private boolean boardDelete(
        @RequestParam Integer board_id,
        @RequestParam String password
    ){
        return bs.boardDelte(board_id, password);
    }
}
