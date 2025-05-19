package com.crhistianm.springboot.di.app.springboot_di.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.crhistianm.springboot.di.app.springboot_di.models.Product;
import com.crhistianm.springboot.di.app.springboot_di.repositories.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private Environment env;

    private ProductRepository repository;   

    public ProductServiceImpl(@Qualifier("productRepositoryJson")ProductRepository repository){
        this.repository = repository;
    }




    @Override
    public List<Product> findAll(){
        return repository.findAll().stream().map(p -> {
            Double priceTax = p.getPrice() * env.getProperty("config.price.tax", Double.class);
            Product newProd = (Product) p.clone();
            newProd.setPrice(priceTax.longValue());
            return newProd;


            //p.setPrice(priceTax.longValue());
            //return p;


        }).collect(Collectors.toList());


    }
    @Override
    public Product findById(Long id){
        return repository.findById(id);
    }

}
