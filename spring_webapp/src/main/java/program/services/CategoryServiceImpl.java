package program.services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import program.dto.categories.CategoryCreateDTO;
import program.dto.categories.CategoryItemDTO;
import program.dto.categories.CategoryUpdateDTO;
import program.entities.CategoryEntity;
import program.iterfaces.CategoryService;
import program.mapper.CategoryMapper;
import program.repositories.CategoryRepository;
import program.storage.StorageService;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final StorageService storageService;
    @Override
    public List<CategoryItemDTO> get() {
        var list = categoryMapper.categoriesToCategoryItems(categoryRepository.findAll());
        return list;
    }

    @Override
    public CategoryItemDTO create(CategoryCreateDTO model) {
        var fileName = storageService.saveMultipartFile(model.getFile()); //storageService.save(model.getBase64());
        var category = categoryMapper.categoryCreateToCategory(model);
        category.setImage(fileName);
        categoryRepository.save(category);
        var result = categoryMapper.categoryToCategoryItem(category);
        return result;
    }

    @Override
    public CategoryItemDTO get(int id) {
        var categoryOptinal = categoryRepository.findById(id);
        if(categoryOptinal.isPresent())
        {
            var model = categoryMapper.categoryToCategoryItem(categoryOptinal.get());
            return model;
        }
        return null;
    }

    @Override
    public CategoryItemDTO update(int id, CategoryUpdateDTO model) {
        var categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isPresent())
        {
            var category = categoryOptional.get();
            category.setName(model.getName());
            categoryRepository.save(category);
            var result = categoryMapper.categoryToCategoryItem(category);
            return result;
        }
        return null;
    }

    @Override
    public void delete(int id) {
        CategoryEntity category = categoryRepository.findById(id).get();
        storageService.removeFile(category.getImage());
        categoryRepository.deleteById(id);
    }
}
