package program.dto.products;

import lombok.Data;

import java.util.List;

@Data
public class ProductItemDTO {
    private int id;
    private String name;
    private double price;
    private String description;
    private String category;
    private List<String> files;
}
