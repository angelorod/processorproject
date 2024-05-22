package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Date;

@Entity
public class ContactVerificationInfo {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private String verificationCode;

   @Column(nullable = false, columnDefinition = "TIMESTAMP")
   private Date verificationCodeExpiration;

   @ManyToOne
   @JsonBackReference
   @JoinColumn(name = "processor_id")
   private Processor processor;

   public Long getId() {
       return id;
   }

   public void setId(Long id) {
       this.id = id;
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

   public Processor getProcessor() {
       return processor;
   }

   public void setProcessor(Processor processor) {
       this.processor = processor;
   }
}

