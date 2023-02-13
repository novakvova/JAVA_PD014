package program.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import program.dto.CategoryDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {
    private static List<CategoryDTO> list = new ArrayList<>();
    @GetMapping("/")
    public List<CategoryDTO> index() {
        list.add(new CategoryDTO("Сало"));
        return list;
    }
}
