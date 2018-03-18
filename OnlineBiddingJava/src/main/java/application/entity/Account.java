package application.entity;

import javax.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "accountNumber", unique = true, nullable = false)
    private String accountNumber;

    @Column(name = "bidPoints", nullable = false)
    private float bidPoints;

    @OneToOne
    private User user;

    public Account(){}

    public void deposit(float sum){
        this.bidPoints += sum;
    }

    public void withdraw(float sum){
        this.bidPoints -= sum;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public float getBidPoints() {
        return bidPoints;
    }

    public void setBidPoints(float bidPoints) {
        this.bidPoints = bidPoints;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
