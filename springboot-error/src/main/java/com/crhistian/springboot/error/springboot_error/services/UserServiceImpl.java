package com.crhistian.springboot.error.springboot_error.services;

import java.lang.classfile.ClassFile.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.crhistian.springboot.error.springboot_error.models.domain.User;

@Service
public class UserServiceImpl implements UserService{

    private List<User> users;
    public UserServiceImpl(){


        this.users = new ArrayList<>();
        users.add(new User(1L, "Pepe", "Gonzales"));
        users.add(new User(2L, "Andres", "Mena"));
        users.add(new User(3L, "Maria", "Perez"));
        users.add(new User(4L, "Josefa", "Ramirez"));
        users.add(new User(5L, "Ale", "Gutierrez"));
    }
    
    


    @Override
    public List<User> findyAll() {
        return users;
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = null;
        for (User u: users) {
            if (u.getId().equals(id)) {
               user = u;
               break;
            } 
        }

        return Optional.ofNullable(user); 
    }


}
