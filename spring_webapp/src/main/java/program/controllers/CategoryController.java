package program.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import program.dto.categories.CategoryCreateDTO;
import program.dto.categories.CategoryItemDTO;
import program.dto.categories.CategoryUpdateDTO;
import program.entities.CategoryEntity;
import program.mapper.CategoryMapper;
import program.repositories.CategoryRepository;
import program.storage.StorageService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final StorageService storageService;

    @GetMapping
    public ResponseEntity<List<CategoryItemDTO>> index() {
        var list = categoryMapper.categoriesToCategoryItems(categoryRepository.findAll());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<CategoryItemDTO> create(@RequestBody CategoryCreateDTO model) {
        var fileName = storageService.save(model.getBase64());
        var category = categoryMapper.categoryCreateToCategory(model);
        category.setImage(fileName);
        categoryRepository.save(category);
        var result = categoryMapper.categoryToCategoryItem(category);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<CategoryEntity> getCagegoryById(@PathVariable("id") int categoryId) {
        var categoryOptinal = categoryRepository.findById(categoryId);
        if(categoryOptinal.isPresent())
        {
            return new ResponseEntity<>(categoryOptinal.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    @PutMapping("{id}")
    public ResponseEntity<CategoryEntity> update(@PathVariable("id") int categoryId,
                                                 @RequestBody CategoryUpdateDTO model) {
        var categoryOptinal = categoryRepository.findById(categoryId);
        if(categoryOptinal.isPresent())
        {
            var category = categoryOptinal.get();
            category.setName(model.getName());
            categoryRepository.save(category);
            return new ResponseEntity<>(category, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int categoryId) {
        categoryRepository.deleteById(categoryId);
        return new ResponseEntity<>("Катагорія знищена.", HttpStatus.OK);
    }
}

