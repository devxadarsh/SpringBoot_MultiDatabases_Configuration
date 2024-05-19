package com.devxadarsh.springbootmultidatabasesconfiguration.entity.mysqlentity;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.GetMapping;

@Table(name = "products")
@Entity
public class Product {

    @Id
    private Integer id;

    private String name;

    private Integer price;

    public Product() {
    }

    public Product(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
