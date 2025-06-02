package com.crhistianm.springboot.jpa.springboot_jpa;

import java.awt.image.RescaleOp;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.crhistianm.springboot.jpa.springboot_jpa.dto.PersonDto;
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
        //delete2();
        //personalizedQueries();
        //personalizedQueries2();
        //personalizedQueriesDistinct();
        //personalizedQueriesConcatUpperAndLowerCase();
        personalizedQueriesBetween();

    }

    @Transactional(readOnly = true)
    public void personalizedQueriesBetween(){

          System.out.println("============= consultas por rangos ============= ");
          List<Person> persons = repository.findAllBetweenId(2L, 5L);
          persons.forEach(System.out::println);



        /*System.out.println("============= consultas por rangos ============= ");
        List<Person> persons = repository.findByIdBetween(2L, 5L);
        persons.forEach(System.out::println);

        System.out.println("============= consultas por nombres ============= ");
        persons = repository.findByNameBetween("J", "Q");
        persons.forEach(System.out::println);*/

    }

    @Transactional(readOnly = true)
    public void personalizedQueriesConcatUpperAndLowerCase(){
        System.out.println("============= consultas con nombres  y apellidos de personas============= ");
        List<String> names = repository.findAllFullNameConcat();
        names.forEach(System.out::println);


        System.out.println("============= consultas nombres y apellidos mayuscula de personas============= ");
        names = repository.findAllFullNameConcatUpper();
        names.forEach(System.out::println);

        System.out.println("============= consultas nombres y apellidos minuscula de personas============= ");
        names = repository.findAllFullNameConcatLower();
        names.forEach(System.out::println);

        System.out.println("============= consultas personalizada person upper y lower case============= ");
        List<Object[]> regs = repository.findPersonDataListCase();
        regs.forEach(p-> {
            System.out.println("id=" + p[0] + ", nombre =" + p[1] + ", apellido=" + p[2] + ", lenguaje=" + p[3]);
        });

    }


    @Transactional(readOnly = true)
    public void personalizedQueriesDistinct(){
        System.out.println("consultas con nombres de personas");
        List<String> names = repository.findAllNames();
        names.forEach(System.out::println);

        System.out.println("============= consultas con nombres unicos de personas============= ");
        names = repository.findAllNamesDistinct();
        names.forEach(System.out::println);

        System.out.println("============= consultas con lenguajes de programacion unicos de personas============= ");
        List<String> languages= repository.findAllProgrammingLanguageDistinct();

        languages.forEach(System.out::println);


        System.out.println("============= consultas con cuenta de lenguajes de programacion unicos de personas============= ");
        Long totalLanguage = repository.findAllProgrammingLanguageDistinctCount();
        System.out.println("Cantidad de lenguajes: " + totalLanguage);

    }

    @Transactional(readOnly = true)
    public void personalizedQueries2(){

        System.out.println("============= consulta por objeto persona y lenguaje programacion ================");
        List<Object[]> personsRegs = repository.findAllMixPerson();

        personsRegs.forEach(reg ->{
            System.out.println("programmingLanguage=" + reg[1] + ", person=" + reg[0]);
        });

        System.out.println("consulta que puebla y devuelve un objeto entity de una instancia personalizada");
        List<Person> persons = repository.findAllObjectPersonPersonalized();
        persons.forEach(System.out::println);
        
        System.out.println("consulta que puebla y devuelve un objeto dto de una clase dto personalizada");

        List<PersonDto> personDtos = repository.findAllPersonDto();

        personDtos.forEach(System.out::println);

    }

    @Transactional(readOnly = true)
    public void personalizedQueries(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("============= consulta nombre por id ================");
        System.out.println("Ingrese el id para obtener el nombre");
        Long id = scanner.nextLong();
        scanner.close();
        String name = repository.getNameById(id);
        System.out.println(name);


        Long idDb = repository.getIdById(id);
        System.out.println(idDb);


        String fullname = repository.getFullNameById(id);
        System.out.println(fullname);

        System.out.println("================== consulta por campos personalizados por el id =============");

        Optional<Object> optionalPersonReg = repository.obtenerPersonDataFullById(id);
        if (optionalPersonReg.isPresent()){
            Object[] personReg = (Object[])optionalPersonReg.orElseThrow();
            System.out.println("id=" + personReg[0] + ", nombre =" + personReg[1] + ", apellido=" + personReg[2] + ", lenguaje=" + personReg[3]);
        }



        System.out.println("================== consulta por campos personalizados lista =============");

        List<Object[]> regs = repository.obtenerPersonDataFull();
        regs.forEach(p-> {
            System.out.println("id=" + p[0] + ", nombre =" + p[1] + ", apellido=" + p[2] + ", lenguaje=" + p[3]);
        });



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
