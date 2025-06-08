package com.crhistianm.springboot.jpa.springboot_jpa_relationship;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.crhistianm.springboot.jpa.springboot_jpa_relationship.entities.Client;
import com.crhistianm.springboot.jpa.springboot_jpa_relationship.entities.Invoice;
import com.crhistianm.springboot.jpa.springboot_jpa_relationship.repositories.ClientRepository;
import com.crhistianm.springboot.jpa.springboot_jpa_relationship.repositories.InvoiceRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner{


    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        manyToOneFindByIdClient();
        
    }

    
    public void manyToOne(){
        Client client = new Client("Jhon", "Doe");
        clientRepository.save(client);



        Invoice invoice = new Invoice("Compras de oficina", 2000L);
        invoice.setClient(client);

        Invoice invoiceDb = invoiceRepository.save(invoice);

        System.out.println(invoiceDb);


    }

    public void manyToOneFindByIdClient(){


    }

}
