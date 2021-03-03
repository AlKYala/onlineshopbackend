package de.yalama.onlineshopbackend.User.controller;

import de.yalama.onlineshopbackend.User.model.User;
import de.yalama.onlineshopbackend.User.service.UserService;
import de.yalama.onlineshopbackend.shared.Controller.BaseController;
import de.yalama.onlineshopbackend.shared.models.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController implements BaseController<User, Long> {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @GetMapping
    public List<User> findAll() {
        return this.userService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return this.userService.findById(id);
    }

    @Override
    @PostMapping
    public User create(@RequestBody User user) {
        return this.userService.save(user);
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return this.userService.save(user);
    }

    @Override
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return this.userService.update(id, user);
    }

    @Override
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return this.userService.deleteById(id);
    }

    @GetMapping("/email/{email}")
    public User findByEmail(@PathVariable String email) {
        return this.userService.findByEmail(email);
    }

    @GetMapping("/email/isTaken/{email}")
    public Boolean isEmailTaken(@PathVariable String email) {
        try {
            this.userService.findByEmail(email);
        } catch (NotFoundException e) {
            return false;
        }
        return true;
    }

    @GetMapping("/username/{username}")
    public User findByUsername(@PathVariable String username) {
        return this.userService.findByUsername(username);
    }

    @GetMapping("/username/isTaken/{username}")
    public Boolean isUsernameTaken(@PathVariable String username) {
        try {
            this.userService.findByUsername(username);
        } catch (NotFoundException e) {
            return false;
        }
        return true;
    }
}
