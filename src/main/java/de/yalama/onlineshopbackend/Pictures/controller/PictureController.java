package de.yalama.onlineshopbackend.Pictures.controller;

import de.yalama.onlineshopbackend.Pictures.model.Picture;
import de.yalama.onlineshopbackend.Pictures.service.PictureService;
import de.yalama.onlineshopbackend.shared.Controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/picture")
@RequiredArgsConstructor
public class PictureController implements BaseController<Picture, Long> {

    @Autowired
    private PictureService pictureService;

    @Override
    @GetMapping
    public List<Picture> findAll() {
        return this.pictureService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Picture findById(@PathVariable Long id) {
        return this.pictureService.findById(id);
    }

    @Override
    @PostMapping
    public Picture create(@RequestBody Picture picture) {
        return this.pictureService.save(picture);
    }

    @Override
    @PutMapping("/{id}")
    public Picture update(@PathVariable Long id, @RequestBody Picture picture) {
        return this.pictureService.update(id, picture);
    }

    @Override
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return this.pictureService.deleteById(id);
    }
}
