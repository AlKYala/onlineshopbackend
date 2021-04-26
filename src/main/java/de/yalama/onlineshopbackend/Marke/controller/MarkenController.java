package de.yalama.onlineshopbackend.Marke.controller;

import de.yalama.onlineshopbackend.Marke.model.Marke;
import de.yalama.onlineshopbackend.Marke.service.MarkeService;
import de.yalama.onlineshopbackend.shared.Controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marke")
@RequiredArgsConstructor
public class MarkenController implements BaseController<Marke, Long> {

    @Autowired
    private MarkeService markeService;

    @Override
    @GetMapping
    public List<Marke> findAll() {
        return this.markeService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Marke findById(@PathVariable Long id) {
        return this.markeService.findById(id);
    }

    @Override
    @PostMapping
    public Marke create(@RequestBody Marke marke) {
        return this.markeService.save(marke);
    }

    @Override
    @PutMapping("/{id}")
    public Marke update(@PathVariable Long id, @RequestBody Marke marke) {
        return this.markeService.update(id, marke);
    }

    @Override
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return this.markeService.deleteById(id);
    }

    @GetMapping("/exists/{name}")
    public Boolean existsByName(@PathVariable String name) {
        return this.markeService.existsByName(name.toLowerCase());
    }
}
