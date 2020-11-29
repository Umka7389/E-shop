package ru.gb.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(EShopApplication.class, args);
    }
// TODO:
// +1. Сделать проверку, чтобы количество продуктов, не могло уйти в отрицательное значение в корзине
//  +2. Проверка, что в поле номер телефона, вводятся только цифры
// 3. Зацикливание при сохранение, сохраняет слишком много продуктов. Поправить.
}
