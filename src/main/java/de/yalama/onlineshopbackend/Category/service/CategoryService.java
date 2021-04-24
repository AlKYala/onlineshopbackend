package de.yalama.onlineshopbackend.Category.service;

import de.yalama.onlineshopbackend.Category.model.Category;
import de.yalama.onlineshopbackend.shared.service.BaseService;

public abstract class CategoryService implements BaseService<Category> {
    public abstract Boolean existsByName(String name);
}
