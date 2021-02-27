package de.yalama.onlineshopbackend.User.controller;

import de.yalama.onlineshopbackend.User.model.User;
import de.yalama.onlineshopbackend.User.service.UserService;
import de.yalama.onlineshopbackend.shared.Controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController implements BaseController<User, Long> {

    @Autowired
    private UserService userService;

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
}
