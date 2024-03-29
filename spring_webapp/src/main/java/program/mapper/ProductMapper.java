package program.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import program.dto.products.ProductItemDTO;
import program.entities.ProductEntity;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.name", target = "category")
    @Mapping(source = "category.id", target = "category_id")
    ProductItemDTO ProductItemDTOByProduct(ProductEntity product);
}
