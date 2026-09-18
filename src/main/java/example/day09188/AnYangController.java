package example.day09188;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@CrossOrigin (value="http://localhost:5173")
@RestController @RequiredArgsConstructor 
public class AnYangController {
    private final AnYangService as;

    @GetMapping ("/view")
    public Map<String,Object> findAll(){
        return as.findAll();
    }

}
