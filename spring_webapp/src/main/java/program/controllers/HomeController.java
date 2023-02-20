package program.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import program.dto.CategoryDTO;
import program.dto.categories.CategoryCreateDTO;
import program.entities.CategoryEntity;
import program.repositories.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class HomeController {
    private CategoryRepository categoryRepository;
    @GetMapping
    public ResponseEntity<List<CategoryEntity>> index() {
        var list = categoryRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryEntity> create(@RequestBody CategoryCreateDTO model) {
        var category = new CategoryEntity();
        category.setName(model.getName());
        categoryRepository.save(category);
        var list = categoryRepository.findAll();
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }
}
