package API.marketplace.models;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Announce{ 
    private @Id @GeneratedValue Long id;
    private String name;
    private String description;
    private String type;
    private String seller;
    private float value;

    Announce() {} 

    Announce(Long id, String name, String description, String type, String seller, float value) {
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

    @Override 
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Announce)) {
            return false;
        }

        Announce todo = (Announce) o;
        return Objects.equals(this.id, todo.id)
            && Objects.equals(this.name, todo.name)
            && Objects.equals(this.description, todo.description)
            && Objects.equals(this.type, todo.type)
            && Objects.equals(this.seller, todo.seller)
            && Objects.equals(this.value, todo.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.description, this.type, this.seller, this.value);
        
    }

    @Override
    public String toString() {
        return "Announce{id=" + this.id + ", name=" + this.name + ", description=" + this.description + 
            ", type=" + this.type + ", seller=" + this.seller + ", value=" + this.value + "}";
    }
}
