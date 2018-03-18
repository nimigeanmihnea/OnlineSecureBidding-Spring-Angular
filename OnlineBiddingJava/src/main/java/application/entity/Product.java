package application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "series", nullable = false, unique = true)
    private long series;

    @Column(name = "producer", nullable = false)
    private String producer;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "specs", nullable = false)
    private String specs;

    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSeries() {
        return series;
    }

    public void setSeries(long series) {
        this.series = series;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    @Override
    public String toString() {
        return "Product{" +
                "series=" + series +
                ", producer='" + producer + '\'' +
                ", name='" + name + '\'' +
                ", specs='" + specs + '\'' +
                '}';
    }
}
