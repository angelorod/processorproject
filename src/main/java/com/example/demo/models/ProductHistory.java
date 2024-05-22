package com.example.demo.models;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "product_history")
public class ProductHistory {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;

    Long userId;
    String userName;
    Timestamp time;
    String product;
    String variety;
    boolean isGround;
    boolean isSmoked;
    String location;
    boolean isDeleted;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "animal_id")
    Animal animal;

    public ProductHistory() {

    }

    public ProductHistory(Long userId, String userName, Timestamp time, String product, String variety, boolean isGround, boolean isSmoked, String location, boolean isDeleted) {
        this.userId = userId;
        this.userName = userName;
        this.time = time;
        this.product = product;
        this.variety = variety;
        this.isGround = isGround;
        this.isSmoked = isSmoked;
        this.location = location;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public boolean getIsGround() {
        return isGround;
    }

    public void setIsGround(boolean isGround) {
        this.isGround = isGround;
    }

    public boolean getIsSmoked() {
        return isSmoked;
    }

    public void setIsSmoked(boolean isSmoked) {
        this.isSmoked = isSmoked;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

}
