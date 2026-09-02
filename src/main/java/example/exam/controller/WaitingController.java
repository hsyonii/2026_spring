package example.exam.controller;

import org.springframework.web.bind.annotation.*;

import example.exam.model.dao.WaitingDao;
import example.exam.model.dto.WaitingDto;

import java.util.ArrayList;

@RestController
public class WaitingController {

    private WaitingDao wd = WaitingDao.getInstance();

    // [1] 등록
    @PostMapping("/waiting/save")
    public boolean save(@ModelAttribute WaitingDto waitingDto) {
        return wd.save(waitingDto);
    }

    // 2. 전체 조회
    @GetMapping("/waiting/findall")
    public ArrayList<WaitingDto> findAll() {
        return wd.findAll(); 
    }

    // 3. 개별 수정
    @PutMapping("/waiting/update")
    public boolean update(@RequestParam("no") int no, @RequestParam("headcount") int headcount) {
        WaitingDto waitingDto = new WaitingDto();
        waitingDto.setNo(no);
        waitingDto.setHeadcount(headcount);
        return wd.update(waitingDto);
    }

    // 4. 개별 삭제
    @DeleteMapping("/waiting/delete")
    public boolean delete(@RequestParam("no") int no) {
        return wd.delete(no);
    }
}