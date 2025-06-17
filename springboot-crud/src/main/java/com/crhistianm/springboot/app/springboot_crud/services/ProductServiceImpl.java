package com.crhistianm.springboot.app.springboot_crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crhistianm.springboot.app.springboot_crud.entities.Product;


@Service
public class ProductServiceImpl implements ProductService{

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return null;
    } 

    @Transactional
    @Override
    public void delete(Product product) {
    }

}
