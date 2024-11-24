package API.marketplace;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
class Product {
    private @Id @GeneratedValue Long id;
    private String name;
    private String description;
    private String type;
    private String seller;
    private float value;

    Product() {} 

    Product(Long id, String name, String description, String type, String seller, float value) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.seller = seller;
        this.value = value;
    } 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
