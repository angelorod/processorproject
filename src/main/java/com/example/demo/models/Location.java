package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Location {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private boolean isPublic;

  @Column(nullable = false)
  private String name;

  @ManyToOne
  @JsonBackReference
  @JoinColumn(name = "processor_id")
  private Processor processor;

  // Getters and Setters

  public Long getId() {
      return id;
  }

  public void setId(Long id) {
      this.id = id;
  }

  public boolean getIsPublic() {
      return isPublic;
  }

  public void setIsPublic(boolean isPublic) {
      this.isPublic = isPublic;
  }

  public String getName() {
      return name;
  }

  public void setName(String name) {
      this.name = name;
  }

  public Processor getProcessor() {
      return processor;
  }

  public void setProcessor(Processor processor) {
      this.processor = processor;
  }
}
