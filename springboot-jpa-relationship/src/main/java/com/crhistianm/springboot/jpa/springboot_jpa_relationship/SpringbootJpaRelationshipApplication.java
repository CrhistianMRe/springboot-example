package com.crhistianm.springboot.jpa.springboot_jpa_relationship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.crhistianm.springboot.jpa.springboot_jpa_relationship.repositories.ClientRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner{


    @Autowired
    private ClientRepository clientRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        
        manyToOne();
    }

    
    public void manyToOne(){

    }


}
