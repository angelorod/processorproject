package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class ProductVariety {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private String flavor;

   @ManyToOne
   @JoinColumn(name = "product_id")
   private Product product;

   @OneToMany(mappedBy = "productVariety", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
   @JsonManagedReference
   private List<ProductVarietyPricing> pricing = new ArrayList<>();

   public Long getId() {
       return id;
   }

   public void setId(Long id) {
       this.id = id;
   }

   public String getFlavor() {
       return flavor;
   }

   public void setFlavor(String flavor) {
       this.flavor = flavor;
   }

   public Product getProduct() {
       return product;
   }

   public void setProduct(Product product) {
       this.product = product;
   }

   public List<ProductVarietyPricing> getPricing() {
       return pricing;
   }

   public void setPricing(List<ProductVarietyPricing> pricing) {
       this.pricing = pricing;
   }

   public void addPricing(ProductVarietyPricing pricing) {
       pricing.setProductVariety(this);
       this.pricing.add(pricing);
   }

   public void removePricing(ProductVarietyPricing pricing) {
       pricing.setProductVariety(null);
       this.pricing.remove(pricing);
   }
}
