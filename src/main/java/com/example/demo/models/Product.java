package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Product {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private String name;

   private String notes;

   @Column(nullable = false)
   private boolean requiresSmoking;

   @Column(nullable = false)
   private boolean requiresGrinding;

   @OneToMany(mappedBy = "product", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
   @JsonManagedReference
   private List<ProductVariety> varieties = new ArrayList<>();

   @OneToMany(mappedBy = "product", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
   @JsonManagedReference
   private List<ProductPricing> pricing = new ArrayList<>();

   @ElementCollection(targetClass = AnimalPart.class)
   @Enumerated(EnumType.STRING)
   private List<AnimalPart> sourceParts = new ArrayList<>();

   @ElementCollection(targetClass = AnimalType.class)
   @Enumerated(EnumType.STRING)
   private List<AnimalType> animalTypes = new ArrayList<>();

   @ManyToOne
   @JoinColumn(name = "processor_id")
   @JsonBackReference
   private Processor processor;

   public Processor getProcessor() {
       return processor;
   }

   public void setProcessor(Processor processor) {
       this.processor = processor;
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

   public String getNotes() {
       return notes;
   }

   public void setNotes(String notes) {
       this.notes = notes;
   }

   public boolean getRequiresSmoking() {
       return requiresSmoking;
   }

   public void setRequiresSmoking(boolean requiresSmoking) {
       this.requiresSmoking = requiresSmoking;
   }

   public boolean getRequiresGrinding() {
       return requiresGrinding;
   }

   public void setRequiresGrinding(boolean requiresGrinding) {
       this.requiresGrinding = requiresGrinding;
   }

   public List<ProductVariety> getVarieties() {
       return varieties;
   }

   public void setVarieties(List<ProductVariety> varieties) {
       this.varieties = varieties;
   }

   public void addVariety(ProductVariety variety) {
       variety.setProduct(this);
       this.varieties.add(variety);
   }

   public void removeVariety(ProductVariety variety) {
       variety.setProduct(null);
       this.varieties.remove(variety);
   }

   public List<ProductPricing> getPricing() {
       return pricing;
   }

   public void setPricing(List<ProductPricing> pricing) {
       this.pricing = pricing;
   }

   public void addPricing(ProductPricing pricing) {
       pricing.setProduct(this);
       this.pricing.add(pricing);
   }

   public void removePricing(ProductPricing pricing) {
       pricing.setProduct(null);
       this.pricing.remove(pricing);
   }

   public List<AnimalPart> getSourceParts() {
       return sourceParts;
   }

   public void setSourceParts(List<AnimalPart> sourceParts) {
       this.sourceParts = sourceParts;
   }

   public void addSourcePart(AnimalPart part) {
       this.sourceParts.add(part);
   }

   public void removeSourcePart(AnimalPart part) {
       this.sourceParts.remove(part);
   }

   public List<AnimalType> getAnimalTypes() {
       return animalTypes;
   }

   public void setAnimalTypes(List<AnimalType> animalTypes) {
       this.animalTypes = animalTypes;
   }

   public void addAnimalType(AnimalType type) {
       this.animalTypes.add(type);
   }

   public void removeAnimalType(AnimalType type) {
       this.animalTypes.remove(type);
   }
}
