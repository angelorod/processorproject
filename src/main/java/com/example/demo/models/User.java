package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"userName", "processor_id"}))
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String userName;

  @Column(nullable = false)
  private boolean useSharedLogin;

  private String name = "";

  private String email = "";

  private String phone = "";

  @JsonIgnore
  private String password;

  @JsonIgnore
  private String verificationCode;

  @JsonIgnore
  @Temporal(TemporalType.TIMESTAMP)
  private Date verificationCodeExpiration;

  @Enumerated(EnumType.STRING)
  private Role role;

  @ManyToOne
  @JsonBackReference
  @JoinColumn(name = "processor_id")
  private Processor processor;

  // Getters and Setters
  public User() {

  }

  public User(String userName, String email, String password) {
    this.userName = userName;
    this.email = email;
    this.password = password;
  }

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

  public String getUserName() {
      return userName;
  }

  public void setUserName(String userName) {
      this.userName = userName;
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

  public String getPhone() {
      return phone;
  }

  public void setPhone(String phone) {
      this.phone = phone;
  }

  public String getPassword() {
      return password;
  }

  public void setPassword(String password) {
      this.password = password;
  }

  public String getVerificationCode() {
      return verificationCode;
  }

  public void setVerificationCode(String verificationCode) {
      this.verificationCode = verificationCode;
  }

  public Date getVerificationCodeExpiration() {
      return verificationCodeExpiration;
  }

  public void setVerificationCodeExpiration(Date verificationCodeExpiration) {
      this.verificationCodeExpiration = verificationCodeExpiration;
  }

  public Role getRole() {
      return role;
  }

  public void setRole(Role role) {
      this.role = role;
  }

    public boolean getUseSharedLogin() {
        return useSharedLogin;
    }

    public void setUseSharedLogin(boolean useSharedLogin) {
        this.useSharedLogin = useSharedLogin;
    }
  
}

