package com.crhistianm.springboot.jpa.springboot_jpa.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.crhistianm.springboot.jpa.springboot_jpa.entities.Person;

/**
 * PersonRepository
 */
public interface PersonRepository extends CrudRepository<Person, Long>{

    List<Person> findByProgrammingLanguage(String programmingLanguage);

    

    
}
