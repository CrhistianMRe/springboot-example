package com.crhistianm.springboot.jpa.springboot_jpa_relationship.repositories;

import org.springframework.data.repository.CrudRepository;

import com.crhistianm.springboot.jpa.springboot_jpa_relationship.entities.Invoice;

/**
 * InvoiceRepository
 */
public interface InvoiceRepository extends CrudRepository<Invoice, Long>{
    
}
