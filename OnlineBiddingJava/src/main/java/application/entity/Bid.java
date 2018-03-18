package application.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Bid implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "bidNumber", nullable = false, unique = true)
    private long bidNumber;

    @Column(name = "status", nullable = false)
    private boolean status = false;

    @OneToOne
    private BidItem item;

    public Bid(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBidNumber() {
        return bidNumber;
    }

    public void setBidNumber(long bidNumber) {
        this.bidNumber = bidNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public BidItem getItem() {
        return item;
    }

    public void setItem(BidItem item) {
        this.item = item;
    }
}
