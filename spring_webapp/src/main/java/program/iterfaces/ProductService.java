package program.iterfaces;


import program.dto.products.ProductCreateDTO;
import program.dto.products.ProductItemDTO;

import java.util.List;

public interface ProductService {
    ProductItemDTO create(ProductCreateDTO model);
    List<ProductItemDTO> get();
}

