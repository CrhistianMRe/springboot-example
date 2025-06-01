package com.crhistianm.springboot.jpa.springboot_jpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.crhistianm.springboot.jpa.springboot_jpa.entities.Person;
import com.crhistianm.springboot.jpa.springboot_jpa.repositories.PersonRepository;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner{

    @Autowired
    private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        //list();
        //findOne();
        create();
    }


    @Transactional(readOnly = true)
    public void findOne(){
        //Person person = null;
        //Optional<Person> optionalPerson = repository.findById(8L);
        //if(optionalPerson.isPresent()){
         //   person = optionalPerson.get();
        //}
        //System.out.println(person);
        
        //repository.findById(1L).ifPresent(person -> System.out.println(person));
        //repository.findOneName("Pepe").ifPresent(System.out::println);
        //repository.findOneLikeName("pe").ifPresent(System.out::println);
        repository.findByNameContaining("pe").ifPresent(System.out::println);

    }



    @Transactional
    public void create(){

        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        String lastname = scanner.next();
        String programmingLanguage = scanner.next();

        scanner.close();

        Person person = new Person(null, name, lastname, programmingLanguage);

        Person personNew = repository.save(person);

        System.out.println(personNew);

        repository.findById(personNew.getId()).ifPresent(System.out::println);
            
    }

    @Transactional(readOnly = true)
    public void list(){
        //List<Person> persons = (List<Person>)repository.findAll();
        //List<Person> persons = (List<Person>)repository.buscarByProgrammingLanguage("Java", "Maria");
        List<Person> persons = (List<Person>)repository.findByProgrammingLanguageAndName("Java", "Maria");

        persons.stream().forEach(person -> {
            System.out.println(person);
        }); 

        List<Object[]> personsValues = repository.obtenerPersonData();

        personsValues.stream().forEach(person -> {
            System.out.println(person [0] + " es experto en " + person[1]);
        }); 
    }


}
