package org.example.controller;

import org.example.model.Role;
import org.example.model.User;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    UserService userService;

    @GetMapping("hello")
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring MVC-SECURITY application");
        messages.add("5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);
        return "hello";
    }

    @GetMapping("login")
    public String loginPage() {

        return "login";
    }


    @GetMapping("admin")
    public String getPage(ModelMap model) {
        model.addAttribute("users", userService.listUser());
        return "index";
    }

    @PostMapping("admin/delete")
    public String deleteUser(ModelMap model, @RequestParam(name = "userId") long id) {
        System.out.println(id);
        if (id == 0) {
            model.addAttribute("id2", "строка id пуста");
            model.addAttribute("users", userService.listUser());
            return "index";
        }
        userService.deleteUser(id);
        model.addAttribute("users", userService.listUser());
        return "index";
    }

    @PostMapping("admin/add")
    public String addUser(ModelMap model,
                          @RequestParam(name = "name") String name,
                          @RequestParam(name = "password") String password,
                          @RequestParam(name = "lastName") String lastName,
                          @RequestParam(name = "firstName") String firstName,
                          @RequestParam(name = "ago") Integer ago,
                          @RequestParam(name = "roles") Set<String> roles/**/) {
        User user = new User(name, password, firstName, lastName, ago);
        Set<Role> roles1 = new HashSet<>();
        roles.forEach(n -> roles1.add(new Role(n, user)));
        user.setRoles(roles1);
        user.getRoles().forEach(n -> System.out.println(n.getRole()));
        userService.saveUser(user);
        model.addAttribute("users", userService.listUser());
        return "index";
    }

    @PostMapping("admin/update")
    public String updateUser(ModelMap model,
                             @RequestParam(name = "id") long id,
                             @RequestParam(name = "name") String name,
                             @RequestParam(name = "password") String password,
                             @RequestParam(name = "lastName") String lastName,
                             @RequestParam(name = "firstName") String firstName,
                             @RequestParam(name = "ago") Integer ago,
                             @RequestParam(name = "roles", required = false) Set<String> roles/**/) {
        User user = new User(name, password, firstName, lastName, ago);
        user.setId(id);
        Set<Role> setRoles = new HashSet<>();
        if ((roles != null)) {
            roles.forEach(n -> setRoles.add(new Role(n, user)));
        } else {
            //setRoles.add(new Role("ROLE_USER", user));
        }

        user.setRoles(setRoles);
        userService.updateUser(user);
        model.addAttribute("users", userService.listUser());
        return "index";
    }

    @GetMapping("user")
    public String getUser(ModelMap model, Principal userS) {
        User user = userService.getUserByName(userS.getName());
        model.addAttribute("messages", user.toString());
        return "user";
    }

}


