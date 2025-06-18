package com.crhistianm.springboot.app.springboot_crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crhistianm.springboot.app.springboot_crud.entities.Product;
import com.crhistianm.springboot.app.springboot_crud.repositories.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    } 
    
    @Transactional
    @Override
    public Optional<Product> update(Long id, Product product) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()){
            Product p = productOptional.orElseThrow();

            p.setSku(product.getSku());
            p.setName(product.getName());
            p.setDescription(product.getDescription());
            p.setPrice(product.getPrice());

            return Optional.of(productRepository.save(p));
        }
        return productOptional;
    }

    @Transactional
    @Override
    public Optional<Product> delete(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        productOptional.ifPresent(p -> productRepository.delete(p));
        return productOptional;
    }

    @Transactional(readOnly = true) 
    @Override
    public boolean existsBySku(String sku) {
        return productRepository.existsBySku(sku);
    }
    

}
