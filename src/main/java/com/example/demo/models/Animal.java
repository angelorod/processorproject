package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tag;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AnimalType type;

    @Enumerated(EnumType.STRING)
    private AnimalCondition arrivalCondition;

    private boolean taxidermyRequested;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm'Z'")
    private Date ageDate;

    @Column(nullable = false)
    private String currentLocation;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm'Z'")
    private Date processingDate;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

    @OneToMany(mappedBy = "animal")
    @JsonManagedReference
    private List<PartStatus> partsToGrind;

    @JsonManagedReference
    @OneToMany(mappedBy = "animal", cascade={ CascadeType.REMOVE, CascadeType.PERSIST }, orphanRemoval=true)
    private List<OrderedProduct> products = new ArrayList<>();

    @OneToMany(mappedBy = "animal")
    @JsonManagedReference
    private List<OrderedPackaging> packaging;

    @OneToMany(mappedBy = "animal")
    @JsonManagedReference
    private List<ButcheringHistory> butcheringHistory;

    @JsonManagedReference
    @OneToMany(mappedBy = "animal")
    List<ProductHistory> productHistory = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "animal")
    List<PackagingHistory> packagingHistory = new ArrayList<>();


    public List<OrderedPackaging> getPackaging() {
        return packaging;
    }

    public void setPackaging(List<OrderedPackaging> packaging) {
        this.packaging = packaging;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public AnimalType getType() {
        return type;
    }

    public void setType(AnimalType type) {
        this.type = type;
    }

    public AnimalCondition getArrivalCondition() {
        return arrivalCondition;
    }

    public void setArrivalCondition(AnimalCondition arrivalCondition) {
        this.arrivalCondition = arrivalCondition;
    }

    public boolean isTaxidermyRequested() {
        return taxidermyRequested;
    }

    public void setTaxidermyRequested(boolean taxidermyRequested) {
        this.taxidermyRequested = taxidermyRequested;
    }

    public Date getAgeDate() {
        return ageDate;
    }

    public void setAgeDate(Date ageDate) {
        this.ageDate = ageDate;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Date getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(Date processingDate) {
        this.processingDate = processingDate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<PartStatus> getPartsToGrind() {
        return partsToGrind;
    }

    public void setPartsToGrind(List<PartStatus> partsToGrind) {
        this.partsToGrind = partsToGrind;
    }

    public List<ButcheringHistory> getButcheringHistory() {
        return butcheringHistory;
    }

    public void setButcheringHistory(List<ButcheringHistory> butcheringHistory) {
        this.butcheringHistory = butcheringHistory;
    }

    public List<OrderedProduct> getProducts() {
        return products;
    }

    public void setProducts(List<OrderedProduct> products) {
        this.products = products;
    }
}