package com.istad.springsecuritybasic.restcontroller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin")
public class AdminRestController {
    @GetMapping
    public String getAllUsers() {
        return "These are all the users ! ";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        return "Delete the user with id " + id + " successfully!";
    }
}
