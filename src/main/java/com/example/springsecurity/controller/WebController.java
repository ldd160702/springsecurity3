package com.example.springsecurity.controller;

import com.example.springsecurity.model.UserEntity;
import com.example.springsecurity.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class WebController {
    @Autowired
    UserDetailsServiceImpl service;

    @RequestMapping(value = {"/", "/home"})
    public ModelAndView homepage(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return new ModelAndView("redirect:/hello");
        }

        return new ModelAndView("home"); // Trả về home.jsp
    }



    @RequestMapping("/admin")
    public ModelAndView admin() {
        ModelAndView adminModelAndView = new ModelAndView("admin");
        adminModelAndView.addObject("admin", "admin");
        List<UserEntity> users = service.getAllUsers();
        adminModelAndView.addObject("users", users);
        return adminModelAndView; // Trả về admin.jsp
    }

    @RequestMapping("/admin/addUser")
    public ModelAndView addUser() {
        ModelAndView adminModelAndView = new ModelAndView("addUser");
        return adminModelAndView; // Trả về admin.jsp
    }

    @RequestMapping("/admin/editUser")
    public ModelAndView editUser() {
        ModelAndView adminModelAndView = new ModelAndView("editUser");
        return adminModelAndView; // Trả về admin.jsp
    }

    @RequestMapping("/user")
    public ModelAndView user() {
        ModelAndView userModelAndView = new ModelAndView("user");
        userModelAndView.addObject("user", "user");
        return userModelAndView; // Trả về user.jsp
    }

    @RequestMapping("/manager")
    public ModelAndView manager() {
        ModelAndView managerModelAndView = new ModelAndView("manager");
        managerModelAndView.addObject("manager", "manager");
        return managerModelAndView; // Trả về manager.jsp
    }

    @RequestMapping("/welcome")
    public ModelAndView hello(Authentication authentication) {
        ModelAndView helloModelAndView = new ModelAndView("welcome");
        helloModelAndView.addObject("username", authentication.getName());
        return helloModelAndView; // Trả về
    }

    @RequestMapping(value = "/admin/addUserHandle", method = RequestMethod.POST)
    public ModelAndView addUser(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                @RequestParam(value = "option1", required = false) String option1,
                                @RequestParam(value = "option2", required = false) String option2,
                                RedirectAttributes redirectAttributes) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        UserEntity user = new UserEntity(username, encoder.encode(password), 1);
        boolean isOption1Selected = "Option 1".equals(option1);
        boolean isOption2Selected = "Option 2".equals(option2);
        if (isOption1Selected) {
            user.addRole("ROLE_MANAGER");
        }

        if (isOption2Selected) {
            user.addRole("ROLE_EMPLOYEE");
        }

        if (!isOption1Selected && !isOption2Selected) {
            redirectAttributes.addFlashAttribute("message", "Please select at least one role");
            return new ModelAndView("redirect:/admin/addUser");
        }

        try {
            if (service.getUserByUsername(username) != null) {
                redirectAttributes.addFlashAttribute("message", "User already exists");
                return new ModelAndView("redirect:/admin");
            }
            service.addUser(user);
            redirectAttributes.addFlashAttribute("message", "User added successfully");
            return new ModelAndView("redirect:/admin");
        } catch (Exception e) {
            // do nothing
            redirectAttributes.addFlashAttribute("message", "Error adding user: " + e.getLocalizedMessage());
            return new ModelAndView("redirect:/admin");
        }

    }

    @RequestMapping(value = "/admin/delete")
    public ModelAndView deleteUser(@RequestParam("username") String username, RedirectAttributes redirectAttributes) {
        service.deleteUserById(username);
        redirectAttributes.addFlashAttribute("message", "User deleted successfully");
        return new ModelAndView("redirect:/admin");
    }

    public boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
    }
}
