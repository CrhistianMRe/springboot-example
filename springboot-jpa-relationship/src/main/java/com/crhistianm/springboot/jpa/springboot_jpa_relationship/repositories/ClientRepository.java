package com.crhistianm.springboot.jpa.springboot_jpa_relationship.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.crhistianm.springboot.jpa.springboot_jpa_relationship.entities.Client;

/**
 * ClientRepository
 */
public interface ClientRepository extends CrudRepository<Client, Long>{

    @Query("select c from Client c join fetch c.addresses where c.id=?1")
    Optional<Client> findOneWithAdresses(Long id);

    @Query("select c from Client c join fetch c.invoices where c.id=?1")
    Optional<Client> findOneWithInvoices(Long id);
}
