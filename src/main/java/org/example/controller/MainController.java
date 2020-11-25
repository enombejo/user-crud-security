package org.example.controller;

import org.example.model.User;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Controller

public class MainController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String getPage(ModelMap model) {
        model.addAttribute("users", userService.listUser());
        return "index";
    }




    @PostMapping(value = "/")
    public String addUser(Model model,
                          @RequestParam(name = "firstName", required = false) String firstName,
                          @RequestParam(name = "lastName", required = false) String lastName,
                          @RequestParam(name = "email", required = false) String email) {
        String str = firstName + " " + lastName + " " + email;
        System.out.println(str);

        if ((firstName == null || firstName == "") && (lastName == null || lastName == "") && (email == null || email == "")) {
            if (firstName == null || firstName == "") model.addAttribute("firstName", "строка first name пуста");
            if (lastName == null || lastName == "") model.addAttribute("lastName", "строка last name пуста");
            if (email == null || email == "") model.addAttribute("email", "строка email пуста");
            model.addAttribute("users", userService.listUser());
            return "index";
        }
        userService.saveUser(new User(firstName, lastName, email));
        model.addAttribute("message", "Добвлен пользователь: " + str);
        model.addAttribute("users", userService.listUser());
        return "index";
    }

    @PostMapping("update")
    public String updateUser(Model model,
                             @RequestParam(name = "id", required = false) long id,
                             @RequestParam(name = "firstName", required = false) String firstName,
                             @RequestParam(name = "lastName", required = false) String lastName,
                             @RequestParam(name = "email", required = false) String email) {

        if ((firstName == null || firstName == "") && (lastName == null || lastName == "") && (email == null || email == "") && (id == 0)) {
            if (id == 0) model.addAttribute("id1", "строка id пуста");
            if (firstName == null || firstName == "") model.addAttribute("firstName1", "строка first name пуста");
            if (lastName == null || lastName == "") model.addAttribute("lastName1", "строка last name пуста");
            if (email == null || email == "") model.addAttribute("email1", "строка email пуста");
            model.addAttribute("users", userService.listUser());
            return "index";
        }

        User user = new User(firstName, lastName, email);
        user.setId(id);
        userService.updateUser(user);
        model.addAttribute("users", userService.listUser());
        return "index";
    }


    @PostMapping("delete")
    public String deleteUser(Model model, @RequestParam(name = "id") long id) {
        if (id == 0) {
            model.addAttribute("id2", "строка id пуста");
            model.addAttribute("users", userService.listUser());
            return "index";
        }
        userService.deleteUser(id);
        model.addAttribute("users", userService.listUser());
        return "index";
    }
}


