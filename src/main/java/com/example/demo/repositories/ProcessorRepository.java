package com.example.demo.repositories;

import com.example.demo.models.Processor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProcessorRepository extends JpaRepository<Processor, Long> {

    Optional<Processor> findByPublicMerchantId(String publicMerchantId);
    Optional<Processor> findByOwner(String owner);
}