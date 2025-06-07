package com.crhistianm.springboot.jpa.springboot_jpa_relationship.entities;

import org.hibernate.property.access.spi.GetterFieldImpl;
import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

/**
 * Invoice
 */
@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Long total;



    public Invoice(Long id, String description, Long total) {
        this.id = id;
        this.description= description;
        this.total= total;
    }

    public Invoice() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getTotal() {
        return total;
    } 

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

