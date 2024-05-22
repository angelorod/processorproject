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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Order {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private String name;

   @Column(nullable = false)
   private String email;

   @Column(nullable = false)
   private String cellPhone;

   @Column(nullable = false, unique = true)
   private String orderUuid;

   @Column(nullable = false)
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm'Z'")
   private Date creationDate;

   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm'Z'")
   private Date completionDate;

   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm'Z'")
   private Date pickUpDate;

   @Column(nullable = false)
   private Integer orderNumber;

   private String verifiedContactInfo;

   @OneToMany(mappedBy = "order", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
   @JsonManagedReference
   private List<Animal> animals = new ArrayList<>();

   @ManyToOne
   @JoinColumn(name = "processor_id")
   @JsonBackReference
   private Processor processor;

   // Getters and setters

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

   public String getEmail() {
       return email;
   }

   public void setEmail(String email) {
       this.email = email;
   }

   public String getCellPhone() {
       return cellPhone;
   }

   public void setCellPhone(String cellPhone) {
       this.cellPhone = cellPhone;
   }

   public String getOrderUuid() {
       return orderUuid;
   }

   public void setOrderUuid(String orderUuid) {
       this.orderUuid = orderUuid;
   }

   public Date getCreationDate() {
       return creationDate;
   }

   public void setCreationDate(Date creationDate) {
       this.creationDate = creationDate;
   }

   public Date getCompletionDate() {
       return completionDate;
   }

   public void setCompletionDate(Date completionDate) {
       this.completionDate = completionDate;
   }

   public Date getPickUpDate() {
       return pickUpDate;
   }

   public void setPickUpDate(Date pickUpDate) {
       this.pickUpDate = pickUpDate;
   }

   public Integer getOrderNumber() {
       return orderNumber;
   }

   public void setOrderNumber(Integer orderNumber) {
       this.orderNumber = orderNumber;
   }

   public String getVerifiedContactInfo() {
       return verifiedContactInfo;
   }

   public void setVerifiedContactInfo(String verifiedContactInfo) {
       this.verifiedContactInfo = verifiedContactInfo;
   }

   public Processor getProcessor() {
       return processor;
   }

   public void setProcessor(Processor processor) {
       this.processor = processor;
   }

   public List<Animal> getAnimals() {
       return animals;
   }

   public void setAnimals(List<Animal> animals) {
       this.animals = animals;
   }

   public void addAnimal(Animal animal) {
       this.animals.add(animal);
       animal.setOrder(this);
   }

   public void removeAnimal(Animal animal) {
       this.animals.remove(animal);
       animal.setOrder(null);
   }
}