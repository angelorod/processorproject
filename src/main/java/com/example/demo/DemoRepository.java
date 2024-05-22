package com.example.demo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemoRepository extends JpaRepository<DemoEntity, Long> {
    // You can add custom query methods here using Query annotations
}