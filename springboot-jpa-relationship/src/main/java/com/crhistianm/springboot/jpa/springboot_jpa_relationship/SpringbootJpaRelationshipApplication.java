package com.crhistianm.springboot.jpa.springboot_jpa_relationship;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner{

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
