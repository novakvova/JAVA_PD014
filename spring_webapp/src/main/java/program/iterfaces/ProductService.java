package program.iterfaces;


import program.dto.products.ProductCreateDTO;
import program.dto.products.ProductItemDTO;

public interface ProductService {
    ProductItemDTO create(ProductCreateDTO model);
}

