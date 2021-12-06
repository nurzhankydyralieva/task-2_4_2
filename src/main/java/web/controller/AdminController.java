package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String printUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "/admin";
    }

    @GetMapping("/new")
    public String addNewUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "new";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") User user, @RequestParam List<String> roles) {
        Set<Role> userRoles = new HashSet<>();
        for (String role : roles) {
            userRoles.add(roleService.getRole(role));
        }
        user.setRoles(userRoles);
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/{id}/update")
    public String update(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "/update";
    }

    @PutMapping("/update/{id}")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam List<String> roles) {
        Set<Role> userRoles = new HashSet<>();
        for (String role : roles) {
            userRoles.add(roleService.getRole(role));
        }
        user.setRoles(userRoles);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}