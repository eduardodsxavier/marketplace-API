package API.marketplace.models;

import org.springframework.hateoas.Link;

public class link {
    public String name;
    public Link link;

    public link(String name, Link link) {
        this.name = name; 
        this.link = link;
    }
}

