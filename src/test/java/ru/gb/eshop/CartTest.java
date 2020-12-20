package ru.gb.eshop;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.gb.eshop.entities.OrderItem;
import ru.gb.eshop.entities.Product;
import ru.gb.eshop.services.CartService;

import java.math.BigDecimal;

@SpringBootTest(classes = CartService.class)
public class CartTest {
    @Autowired
    private CartService cart;

    @Test
    public void cartFillingTest() {
        cart.clear();
        for (int i = 0; i < 5; i++) {
            Product product = new Product();
            product.setId((long) i);
            product.setPrice(new BigDecimal(100 + (long) i * 10));
            product.setTitle("Product #" + (long) i);
            cart.add(product);
        }
        Assertions.assertEquals(5, cart.getItems().size());
    }

    @Test
    public void cartClearTest() {
        for (int i = 0; i < 6; i++) {
            Product product = new Product();
            product.setId((long) i);
            product.setPrice(new BigDecimal(100 + (long) i * 10));
            product.setTitle("Product #" + (long) i);
            cart.add(product);
        }

        Assertions.assertEquals(6, cart.getItems().size());
        cart.clear();
        Assertions.assertEquals(0, cart.getItems().size());
    }

    @Test
    public void cartRecalculateTest() {
        cart.clear();
        for (int i = 0; i < 6; i++) {
            Product product = new Product();
            product.setId((long) i);
            product.setPrice(new BigDecimal(100 + (long) i * 10));
            product.setTitle("Product #" + (long) i);
            cart.add(product);
        }

        Assertions.assertEquals(BigDecimal.valueOf(750), cart.getPrice());
        cart.getItems().remove(0);
        Assertions.assertEquals(5, cart.getItems().size());
        cart.recalculate();
        Assertions.assertEquals(BigDecimal.valueOf(650), cart.getPrice());

    }

    @Test
    public void cartRemoveTest() {
        cart.clear();
        for (int i = 0; i < 6; i++) {
            Product product = new Product();
            product.setId((long) i);
            product.setPrice(new BigDecimal(100 + (long) i * 10));
            product.setTitle("Product #" + (long) i);
            cart.add(product);
        }
        Long id = cart.getItems().get(0).getProduct().getId();
        cart.getItems().remove(0);
        for (OrderItem orderItem: cart.getItems()){
            if (orderItem.getProduct().getId()==id){
                Assertions.fail();
            }
        }

    }
}
