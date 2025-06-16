package com.crhistianm.springboot.jpa.springboot_jpa_relationship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.crhistianm.springboot.jpa.springboot_jpa_relationship.entities.Address;
import com.crhistianm.springboot.jpa.springboot_jpa_relationship.entities.Client;
import com.crhistianm.springboot.jpa.springboot_jpa_relationship.entities.ClientDetails;
import com.crhistianm.springboot.jpa.springboot_jpa_relationship.entities.Course;
import com.crhistianm.springboot.jpa.springboot_jpa_relationship.entities.Invoice;
import com.crhistianm.springboot.jpa.springboot_jpa_relationship.entities.Student;
import com.crhistianm.springboot.jpa.springboot_jpa_relationship.repositories.ClientDetailsRepository;
import com.crhistianm.springboot.jpa.springboot_jpa_relationship.repositories.ClientRepository;
import com.crhistianm.springboot.jpa.springboot_jpa_relationship.repositories.CourseRepository;
import com.crhistianm.springboot.jpa.springboot_jpa_relationship.repositories.InvoiceRepository;
import com.crhistianm.springboot.jpa.springboot_jpa_relationship.repositories.StudentRepository;


@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner{

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ClientDetailsRepository clientDetailsRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        manyToManyRemove();
    }


   

    @Transactional
    public void manyToManyRemove(){
        Student student1 = new Student("Jano", "Pura");
        Student student2 = new Student("Erba", "Doe");

        Course course1 = new Course("Curso de Java master", "Andres");
        Course course2 = new Course("Curso de Spring Boot" , "Andres");


        student1.setCourses(Set.of(course1,course2));
        student2.setCourses(Set.of(course1));

        studentRepository.saveAll(Set.of(student1,student2));


        Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(3L);
        if(studentOptionalDb.isPresent()){
            Student studentDb = studentOptionalDb.get();
            Optional<Course> courseOptionalDb = courseRepository.findById(3L);

            if(courseOptionalDb.isPresent()){
                studentDb.getCourses().remove(courseOptionalDb.get());
                studentRepository.save(studentDb);
                System.out.println(studentDb);
            }
        }

    }


    @Transactional
    public void manyToManyRemoveFind(){

        Optional<Student> optionalStudent1 = studentRepository.findById(1L);
        Optional<Student> optionalStudent2 = studentRepository.findById(2L);

        Course course1 = courseRepository.findById(1L).get();
        Course course2 = courseRepository.findById(2L).get();

        optionalStudent1.ifPresent(s1 -> {
            s1.setCourses(Set.of(course1,course2));
            studentRepository.saveAll(Set.of(s1));
            System.out.println(s1);
        });

        optionalStudent2.ifPresent(s2 -> {
            s2.setCourses(Set.of(course1));
            studentRepository.saveAll(Set.of(s2));
            System.out.println(s2);
        });


        Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(1L);
        if(studentOptionalDb.isPresent()){
            Student studentDb = studentOptionalDb.get();
            Optional<Course> courseOptionalDb = courseRepository.findById(2L);

            if(courseOptionalDb.isPresent()){
                studentDb.getCourses().remove(courseOptionalDb.get());
                studentRepository.save(studentDb);
                System.out.println(studentDb);
            }
        }
    }

    @Transactional
    public void manyToManyFind(){
        Optional<Student> optionalStudent1 = studentRepository.findById(1L);
        Optional<Student> optionalStudent2 = studentRepository.findById(2L);

        Course course1 = courseRepository.findById(1L).get();
        Course course2 = courseRepository.findById(2L).get();

        optionalStudent1.ifPresent(s1 -> {
            s1.setCourses(Set.of(course1,course2));
            studentRepository.saveAll(Set.of(s1));
            System.out.println(s1);
        });

        optionalStudent2.ifPresent(s2 -> {

            s2.setCourses(Set.of(course1));
            studentRepository.saveAll(Set.of(s2));
            System.out.println(s2);
        });


    }

    @Transactional
    public void manyToMany(){
        Student student1 = new Student("Jano", "Pura");
        Student student2 = new Student("Erba", "Doe");

        Course course1 = new Course("Curso de Java master", "Andres");
        Course course2 = new Course("Curso de Spring Boot" , "Andres");


        student1.setCourses(Set.of(course1,course2));
        student2.setCourses(Set.of(course1));

        studentRepository.saveAll(Set.of(student1,student2));

    }


    @Transactional
    public void oneToOneBidireccionalFindById(){
        Optional<Client> optionalClient = clientRepository.findOne(2L);

        optionalClient.ifPresent(client -> {
            ClientDetails clientDetails = new ClientDetails(true, 5000);

            client.setClientDetails(clientDetails);
            clientRepository.save(client);

            System.out.println(client);
        });
    }


    @Transactional
    public void oneToOneBidireccional(){
        Client client = new Client("Erba", "Pura");
        ClientDetails clientDetails = new ClientDetails(true, 5000);

        client.setClientDetails(clientDetails);

        clientRepository.save(client);

        System.out.println(client);

    }



    @Transactional
    public void oneToOneFindById(){
        ClientDetails clientDetails = new ClientDetails(true, 5000);
        clientDetailsRepository.save(clientDetails);

        Optional<Client> clientOptional = clientRepository.findOne(2L);//new Client("Erba", "Pura");
        clientOptional.ifPresent(client -> {

            client.setClientDetails(clientDetails);
            clientRepository.save(client);

            System.out.println(client);

        });                                                            
    }

    @Transactional
    public void oneToOne(){
        Client client = new Client("Erba", "Pura");
        ClientDetails clientDetails = new ClientDetails(true, 5000);
        client.setClientDetails(clientDetails);

        clientDetailsRepository.save(clientDetails);
        clientRepository.save(client);

    }


    @Transactional
    public void removeInvoiceBidireccional(){

        Optional<Client> optionalClient = Optional.of(new Client("Fran", "Moras"));
        optionalClient.ifPresent(client -> {

            Invoice invoice1 = new Invoice("compras de la casa", 5000L);
            Invoice invoice2 = new Invoice("compras de oficina", 8000L);

            client.addInvoice(invoice1).addInvoice(invoice2);

            clientRepository.save(client);
            System.out.println(client);
        });

        Optional<Client> optionalClientBd = clientRepository.findOne(3L);
        optionalClientBd.ifPresent(client -> {
            Optional<Invoice> invoiceOptional = invoiceRepository.findById(2L);
            invoiceOptional.ifPresent(invoice ->{
                client.removeInvoice(invoice);
                clientRepository.save(client);
                System.out.println(client);

            });
            });
    }


    @Transactional
    public void removeInvoiceBidireccionalFindById(){
        Optional<Client> optionalClient = clientRepository.findOne(1L);
        optionalClient.ifPresent(client -> {

            Invoice invoice1 = new Invoice("compras de la casa", 5000L);
            Invoice invoice2 = new Invoice("compras de oficina", 8000L);

            client.addInvoice(invoice1).addInvoice(invoice2);

            clientRepository.save(client);
            System.out.println(client);
        });

        Optional<Client> optionalClientBd = clientRepository.findOne(1L);
        optionalClientBd.ifPresent(client -> {
            Optional<Invoice> invoiceOptional = invoiceRepository.findById(2L);
            invoiceOptional.ifPresent(invoice ->{
                client.removeInvoice(invoice);
                clientRepository.save(client);
                System.out.println(client);

            });
            });
    }

    @Transactional
    public void oneToManyBidireccionalFindById(){
        Optional<Client> optionalClient = clientRepository.findOne(1L);
        optionalClient.ifPresent(client -> {

            Invoice invoice1 = new Invoice("compras de la casa", 5000L);
            Invoice invoice2 = new Invoice("compras de oficina", 8000L);

            client.addInvoice(invoice1).addInvoice(invoice2);

            clientRepository.save(client);
            System.out.println(client);
        });
    }


    @Transactional
    public void oneToManyBidireccional(){
        Client client = new Client("Fran", "Moras");

        Invoice invoice1 = new Invoice("compras de la casa", 5000L);
        Invoice invoice2 = new Invoice("compras de oficina", 8000L);



        client.addInvoice(invoice1).addInvoice(invoice2);


        clientRepository.save(client);
        System.out.println(client);
    }

    @Transactional
    public void removeAddressFindById(){
        Optional<Client> optionalClient = clientRepository.findById(2L);
        optionalClient.ifPresent(client -> {
            Address address1 = new Address("El verjel", 1234);
            Address address2 = new Address("Vasco de gama ", 9875);

            Set<Address> addresses = new HashSet<>();
            addresses.add(address1);
            addresses.add(address2);
            client.setAddresses(addresses);

            clientRepository.save(client);

            System.out.println(client);

            Optional<Client> optionalClient2 = clientRepository.findOneWithAddresses(2L);
            optionalClient2.ifPresent(c -> {
                c.getAddresses().remove(address1);
                clientRepository.save(client);
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++=");
                System.out.println(c);
            });

        });

    }


    @Transactional
    public void removeAddress(){

        Client client = new Client("Fran", "Moras");

        Address address1 = new Address("El verjel", 1234);
        Address address2 = new Address("Vasco de gama ", 9875);


        client.getAddresses().add(address1);
        client.getAddresses().add(address2);


        clientRepository.save(client);

        System.out.println(client);

        Optional<Client> optionalClient = clientRepository.findById(3L);
        optionalClient.ifPresent(c -> {

            c.getAddresses().remove(address1);
            clientRepository.save(client);

            System.out.println(c);
        });


    }

    @Transactional
    public void oneToMany(){
        Client client = new Client("Fran", "Moras");

        Address address1 = new Address("El verjel", 1234);
        Address address2 = new Address("Vasco de gama ", 9875);


        client.getAddresses().add(address1);
        client.getAddresses().add(address2);


        clientRepository.save(client);

        System.out.println(client);
    }
    
    @Transactional
    public void oneToManyFindById(){
        Optional<Client> optionalClient = clientRepository.findById(2L);
        optionalClient.ifPresent(client -> {

            Address address1 = new Address("El verjel", 1234);
            Address address2 = new Address("Vasco de gama ", 9875);

            Set<Address> addresses = new HashSet<>();
            addresses.add(address1);
            addresses.add(address2);

            client.setAddresses(addresses);

            clientRepository.save(client);

            System.out.println(client);
        });

    }

    @Transactional
    public void manyToOne(){
        Client client = new Client("Jhon", "Doe");
        clientRepository.save(client);



        Invoice invoice = new Invoice("Compras de oficina", 2000L);
        invoice.setClient(client);

        Invoice invoiceDb = invoiceRepository.save(invoice);

        System.out.println(invoiceDb);


    }

    @Transactional
    public void manyToOneFindByIdClient(){
        Optional<Client> optionalClient = clientRepository.findById(1L);

        if (optionalClient.isPresent()) {
            Client client = optionalClient.orElseThrow();
            Invoice invoice = new Invoice("Compras de oficina", 2000L);
            invoice.setClient(client);
            Invoice invoiceDb = invoiceRepository.save(invoice);
            System.out.println(invoiceDb);
        }
    }

}
