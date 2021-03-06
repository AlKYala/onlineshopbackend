package de.yalama.onlineshopbackend.User.service;

import de.yalama.onlineshopbackend.User.model.User;
import de.yalama.onlineshopbackend.shared.service.BaseService;

public abstract class UserService implements BaseService<User> {
    public abstract User findByEmail(String email);

    public abstract User findByUsername(String username);
}
