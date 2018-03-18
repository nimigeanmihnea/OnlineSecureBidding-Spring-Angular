package application.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class BidItem implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @OneToOne
    private Product product;

    @Column(name = "price", nullable = false)
    private float price;

    @OneToMany
    private List<User> bidders;

    public BidItem(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<User> getBidders() {
        return bidders;
    }

    public void setBidders(List<User> bidders) {
        this.bidders = bidders;
    }
}
