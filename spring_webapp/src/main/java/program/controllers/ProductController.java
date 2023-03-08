package program.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import program.dto.products.ProductCreateDTO;
import program.dto.products.ProductItemDTO;
import program.iterfaces.ProductService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductItemDTO>> index() {
        return new ResponseEntity<>(productService.get(), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductItemDTO> create(@Valid @ModelAttribute ProductCreateDTO model) {
        var result = productService.create(model);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
