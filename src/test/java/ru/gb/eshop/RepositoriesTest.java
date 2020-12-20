package ru.gb.eshop;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import ru.gb.eshop.entities.*;
import ru.gb.eshop.repositories.*;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class RepositoriesTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void productRepositoryTest() {
        Product product = new Product();
        product.setPrice(BigDecimal.TEN);
        product.setTitle("new prod");
        entityManager.persist(product);
        entityManager.flush();

        List<Product> productsList = productRepository.findAll();

        Assertions.assertEquals(5, productsList.size());
        Assertions.assertEquals("Milk", productsList.get(1).getTitle());
    }

    @Test
    public void CategoryRepositoryTest() {
        Category category = new Category();
        category.setTitle("new category");
        entityManager.persist(category);
        entityManager.flush();

        List<Category> categoryList = (List<Category>) categoryRepository.findAll();

        Assertions.assertEquals(3, categoryList.size());
        Assertions.assertEquals("Food", categoryList.get(0).getTitle());
    }

    @Test
    public void OrderItemRepositoryTest() {
        Product product = new Product();
        product.setPrice(BigDecimal.TEN);
        product.setTitle("new prod");
        OrderItem orderItem = new OrderItem(product);
        entityManager.persist(product);
        entityManager.persist(orderItem);
        entityManager.flush();

        List<OrderItem> orderItemList = (List<OrderItem>) orderItemRepository.findAll();

        Assertions.assertEquals(1, orderItemList.size());
        Assertions.assertEquals("new prod", orderItemList.get(0).getProduct().getTitle());
    }

    @Test
    public void OrderRepositoryTest() {
        Product product = new Product();
        product.setPrice(BigDecimal.TEN);
        product.setTitle("new prod");
        OrderItem orderItem = new OrderItem(product);
        User user = new User();
        user.setLastName("user");
        entityManager.persist(product);
        entityManager.persist(orderItem);
        entityManager.persist(user);
        entityManager.flush();

        List<Order> orderList = (List<Order>) orderRepository.findAll();

        Assertions.assertEquals(1, orderList.size());
        Assertions.assertEquals("user", orderList.get(0).getUser().getLastName());
        Assertions.assertEquals(1, orderList.get(0).getItems().size());
        Assertions.assertEquals("new prod", orderList.get(0).getItems().get(0).getProduct().getTitle());
    }

    @Test
    public void RoleRepositoryTest() {
        Role role = new Role();
        role.setName("ROLE_TEST");
        entityManager.persist(role);
        entityManager.flush();

        List<Role> roles = (List<Role>) roleRepository.findAll();

        Assertions.assertEquals(4, roles.size());
        Assertions.assertEquals("ROLE_TEST", roles.get(3).getName());
    }

    @Test
    public void UserRepositoryTest() {
        User user = new User();
        user.setPhone("123");
        user.setEmail("123@mail.ru");
        user.setPassword("123");
        entityManager.persist(user);
        entityManager.flush();

        List<User> users = userRepository.findAll();

        Assertions.assertEquals(2, users.size());
        Assertions.assertEquals("123", users.get(1).getPhone());
        Assertions.assertEquals("123@mail.ru", users.get(1).getEmail());
    }

    @Test
    public void initDbTest() {
        List<Product> productsList = productRepository.findAll();
        Assertions.assertEquals(4, productsList.size());
    }
}
