package ru.gb.eshop.entities.embeded;

import ru.gb.eshop.entities.Product;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Embeddable
public class ProductStartDate implements Serializable {

    @ManyToOne
    @JoinColumn (name = "product_id")
    private Product product;

    @Column (name = "start_date")
    private String startDate;

    public ProductStartDate() {
    }

    public ProductStartDate(Product product, String startDate) {
        this.product = product;
        this.startDate = startDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductStartDate)) return false;
        ProductStartDate that = (ProductStartDate) o;
        return Objects.equals(getProduct(), that.getProduct()) &&
                Objects.equals(getStartDate(), that.getStartDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProduct(), getStartDate());
    }
}
