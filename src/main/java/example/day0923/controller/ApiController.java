package example.day0923.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.day0923.model.dto.ApiDto;
import example.day0923.service.ApiService;
import lombok.RequiredArgsConstructor;


@RestController 
@RequiredArgsConstructor 
@RequestMapping ("/api")
@CrossOrigin ("http://localhost:5174")
public class ApiController {

    private final ApiService apiService;

    @GetMapping("")
    public List<ApiDto> findAll(){
        return apiService.findAll();
    }

    @PostMapping("")
    public boolean save( @RequestBody ApiDto apiDto ){
        return  apiService.save( apiDto );
    }
}
