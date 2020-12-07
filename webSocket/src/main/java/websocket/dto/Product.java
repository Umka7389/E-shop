package websocket.dto;

import lombok.Data;

@Data
public class Product {

    private Long id;
    private String title;
    private float price;

    public Product(Long id, String title, float price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }
}
