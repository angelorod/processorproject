package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordered_products")
public class OrderedProduct {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private String flavor;

    private String units;

    private int orderedQty;

    private boolean isPercentQty;

    private int actualQty;

    private float price;

    private boolean requiresGrinding;

    private boolean requiresSmoking;

    private boolean isCut;

    private boolean isTrimmed;

    private boolean isGround;

    private boolean isSmoked;

    @Enumerated(EnumType.STRING)
    private AnimalPart part;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public int getOrderedQty() {
        return orderedQty;
    }

    public void setOrderedQty(int orderedQty) {
        this.orderedQty = orderedQty;
    }

    public boolean getIsPercentQty() {
        return isPercentQty;
    }

    public void setIsPercentQty(boolean isPercentQty) {
        this.isPercentQty = isPercentQty;
    }

    public int getActualQty() {
        return actualQty;
    }

    public void setActualQty(int actualQty) {
        this.actualQty = actualQty;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean getRequiresGrinding() {
        return requiresGrinding;
    }

    public void setRequiresGrinding(boolean requiresGrinding) {
        this.requiresGrinding = requiresGrinding;
    }

    public boolean getRequiresSmoking() {
        return requiresSmoking;
    }

    public void setRequiresSmoking(boolean requiresSmoking) {
        this.requiresSmoking = requiresSmoking;
    }

    public boolean getIsCut() {
        return isCut;
    }

    public void setIsCut(boolean isCut) {
        this.isCut = isCut;
    }

    public boolean getIsTrimmed() {
        return isTrimmed;
    }

    public void setIsTrimmed(boolean isTrimmed) {
        this.isTrimmed = isTrimmed;
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

    public AnimalPart getPart() {
        return part;
    }

    public void setPart(AnimalPart part) {
        this.part = part;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}