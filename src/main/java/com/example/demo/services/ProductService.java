package com.example.demo.services;

import com.example.demo.models.Product;
import com.example.demo.models.ProductPricing;
import com.example.demo.models.Processor;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.ProcessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProcessorRepository processorRepository;

    public Product createProduct(Product product, Long processorId) {
        Processor processor = processorRepository.findById(processorId).orElse(null);
        if (processor != null) {
            product.setProcessor(processor);
            return productRepository.save(product);
        } else {
            // handle the case where the processor is not found
            return null;
        }
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product addProductPricing(Long productId, ProductPricing pricing) {
        Product product = getProduct(productId);
        if (product != null) {
            product.addPricing(pricing);
            return updateProduct(product);
        } else {
            return null;
        }
    }
}