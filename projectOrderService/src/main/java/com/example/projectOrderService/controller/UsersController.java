package com.example.projectOrderService.controller;

import com.example.projectOrderService.exception.NoSuchUserExceptions;
import com.example.projectOrderService.model.Users;
import com.example.projectOrderService.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/create")
    public Users createUser(@RequestBody Users users) {
        return usersService.createUser(users);
    }

    @GetMapping("/get/{id}")
    public Users getUserById(@PathVariable Long id) {
        Users users = usersService.getOneUser(id);

        if (users == null) {
            throw new NoSuchUserExceptions("User with id = "+id+" not found!");
        }

        return users;
    }

    @PutMapping("/upd/{id}")
    public Users updUser(@PathVariable Long id, @RequestBody Users users) {
        users.setId(id);
        return usersService.updateUser(users);
    }

    @GetMapping("/get-users")
    public List<Users> getUsers() {
        return usersService.getAllUsers();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        Users users = usersService.getOneUser(id);
        if (users == null) {
            throw new NoSuchUserExceptions("User with id = "+id+" not found!");
        } else {
            usersService.deleteUser(id);
        }
    }


    @ExceptionHandler
    public ResponseEntity<String> handleException(NoSuchUserExceptions exceptions){
        String msg = exceptions.getMessage();
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }
}
