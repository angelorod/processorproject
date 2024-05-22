package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class ProductPricing {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private float price;

   @Column(nullable = false)
   private String units;

   @Column
   private float minQty;

   @ManyToOne
   @JoinColumn(name = "product_id")
   private Product product;

   public Long getId() {
       return id;
   }

   public void setId(Long id) {
       this.id = id;
   }

   public float getPrice() {
       return price;
   }

   public void setPrice(float price) {
       this.price = price;
   }

   public String getUnits() {
       return units;
   }

   public void setUnits(String units) {
       this.units = units;
   }

   public float getMinQty() {
       return minQty;
   }

   public void setMinQty(float minQty) {
       this.minQty = minQty;
   }

   public Product getProduct() {
       return product;
   }

   public void setProduct(Product product) {
       this.product = product;
   }
}

