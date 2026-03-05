package com.ecommerce.bcruz.models;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category() {}

    public Category(String name) 
    {
        this.name = name;
    }

    // setters & getters
    public Long getId() { return id; }
    public void setName(String name) { this.name = name; }
	public String getName() { return name; }
}
