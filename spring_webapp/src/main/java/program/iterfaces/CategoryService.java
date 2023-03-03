package program.iterfaces;

import program.dto.categories.CategoryCreateDTO;
import program.dto.categories.CategoryItemDTO;
import program.dto.categories.CategoryUpdateDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryItemDTO> get();
    CategoryItemDTO create(CategoryCreateDTO model);
    CategoryItemDTO get(int id);
    CategoryItemDTO update(int id, CategoryUpdateDTO model);
    void delete(int id);

}
