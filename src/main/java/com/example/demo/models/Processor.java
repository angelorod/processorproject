package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;

@Entity
public class Processor {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private String name;

   @Column(nullable = false, unique = true)
   private String publicMerchantId;

   @Column(nullable = false, unique = true)
   private String owner; // New field

   @OneToMany(mappedBy = "processor", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
   @JsonManagedReference
   private List<User> staff = new ArrayList<>();

   @OneToMany(mappedBy = "processor", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
   @JsonManagedReference
   private List<Location> locations = new ArrayList<>();

   @OneToMany(mappedBy = "processor", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
   @JsonManagedReference
   private List<Unit> units = new ArrayList<>();

   @OneToMany(mappedBy = "processor", cascade = CascadeType.REMOVE, orphanRemoval = true)
   @JsonManagedReference
   private List<ContactVerificationInfo> contactVerificationInfos = new ArrayList<>();

   @OneToMany(mappedBy = "processor", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
   @JsonManagedReference
   private List<Product> products = new ArrayList<>();

   @OneToMany(mappedBy = "processor", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
   @JsonManagedReference
   private List<Order> orders = new ArrayList<>();

   public List<Order> getOrders() {
    return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
        order.setProcessor(this);
    }

    public void removeOrder(Order order) {
        this.orders.remove(order);
        order.setProcessor(null);
    }

   public List<Product> getProducts() {
       return products;
   }

   public void setProducts(List<Product> products) {
       this.products = products;
   }

   public void addProduct(Product product) {
       this.products.add(product);
       product.setProcessor(this);
   }

   public void removeProduct(Product product) {
       this.products.remove(product);
       product.setProcessor(null);
   }

   public List<ContactVerificationInfo> getContactVerificationInfos() {
       return contactVerificationInfos;
   }

   public void setContactVerificationInfos(List<ContactVerificationInfo> contactVerificationInfos) {
       this.contactVerificationInfos = contactVerificationInfos;
   }

   public List<Location> getLocations() {
       return locations;
   }

   public void setLocations(List<Location> locations) {
       this.locations = locations;
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

   public String getPublicMerchantId() {
       return publicMerchantId;
   }

   public void setPublicMerchantId(String publicMerchantId) {
       this.publicMerchantId = publicMerchantId;
   }

   public List<User> getStaff() {
       return staff;
   }

   public void setStaff(List<User> staff) {
       this.staff = staff;
   }

   public void addUserToStaff(User user) {
       this.getStaff().add(user);
       user.setProcessor(this);
   }

   public void addLocation(Location location) {
       this.locations.add(location);
       location.setProcessor(this);
   }

   public void addUnit(Unit unit) {
       this.units.add(unit);
       unit.setProcessor(this);
   }

   public void addContactVerificationInfo(ContactVerificationInfo contactVerificationInfo) {
       this.contactVerificationInfos.add(contactVerificationInfo);
       contactVerificationInfo.setProcessor(this);
   }

public String getOwner() {
    return owner;
}

public void setOwner(String owner) {
    this.owner = owner;
}

public List<Unit> getUnits() {
    return units;
}

public void setUnits(List<Unit> units) {
    this.units = units;
}

   
}

