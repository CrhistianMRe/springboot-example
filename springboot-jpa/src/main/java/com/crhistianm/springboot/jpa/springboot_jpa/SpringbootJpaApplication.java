package com.crhistianm.springboot.jpa.springboot_jpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.aspectj.weaver.patterns.PerObject;
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
        //create();
        //update();
        //delete();
        delete2();
    }

    @Transactional(readOnly = true)
    public void personalizedQueries(){



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
    public void delete2(){

        repository.findAll().forEach(System.out::println);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el id a eliminar");

        Long id = scanner.nextLong();
        Optional<Person> optionalPerson = repository.findById(id);

        optionalPerson.ifPresentOrElse(person -> {
            repository.delete(person);
            System.out.println("Eliminado exitosamente");
        }, () -> System.out.println("No existe el id"));

        repository.findAll().forEach(System.out::println);

        scanner.close();
    }

    @Transactional
    public void delete(){

        repository.findAll().forEach(System.out::println);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el id a eliminar");

        Long id = scanner.nextLong();
        repository.deleteById(id);
        repository.findAll().forEach(System.out::println);
    }
    @Transactional
    public void update(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el id de la persona a editar");

        Long id = scanner.nextLong();

        Optional<Person> optionalPerson = repository.findById(id);

        //optionalPerson.ifPresent(p -> {
        if(optionalPerson.isPresent()){
            Person p = optionalPerson.orElseThrow();

            System.out.println(p);
            System.out.println("Ingrese el lenguaje de progra para modificar");
            String programmingLanguage = scanner.next();
            p.setProgrammingLanguage(programmingLanguage);
            Person personDb = repository.save(p);
            System.out.println(personDb);
        }else{

                System.out.println("El usuario no existe");
        }
        //}); 

        scanner.close();

    }


    @Transactional
    public void create(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre ");
        String name = scanner.next();

        System.out.println("Ingrese lastname");
        String lastname = scanner.next();

        System.out.println("Ingrese programmingLanguage");
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
