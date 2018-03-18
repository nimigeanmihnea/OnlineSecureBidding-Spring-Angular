package application.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Sale{

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "saleNumber", nullable = false, unique = true)
    private long saleNumber;

    @OneToOne
    private Product product;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "date", nullable = false)
    private String date;

    @ManyToOne
    private User user;

    public Sale(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(long saleNumber) {
        this.saleNumber = saleNumber;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
