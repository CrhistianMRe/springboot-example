package com.crhistianm.springboot.jpa.springboot_jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.crhistianm.springboot.jpa.springboot_jpa.entities.Person;

/**
 * PersonRepository
 */
public interface PersonRepository extends CrudRepository<Person, Long>{

    
}
