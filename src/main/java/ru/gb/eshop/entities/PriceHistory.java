package ru.gb.eshop.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.gb.eshop.entities.embeded.ProductStartDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Component
@Table(name = "prices")
@Getter
@Setter
public class PriceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column (name = "start_date")
    private String startDate;

    @Column (name = "end_date")
    private String endDate;

    @Column (name = "price")
    private BigDecimal price;


}
