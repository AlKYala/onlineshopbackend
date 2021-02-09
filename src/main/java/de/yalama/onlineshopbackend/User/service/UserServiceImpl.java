package de.yalama.onlineshopbackend.User.service;

import de.yalama.onlineshopbackend.User.model.User;
import de.yalama.onlineshopbackend.User.repository.UserRepository;

import java.util.List;

public class UserServiceImpl extends UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        //TODO Validator exists, Exception notFound
        return this.userRepository.findById(id).get();
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User save(User instance) {
        //TODO Validator notExists, Exception notSaved
        return this.userRepository.save(instance);
    }

    @Override
    public User update(User instance) {
        //TODO Validator exists, Exception notFound, not Saved
        return this.userRepository.save(instance);
    }

    @Override
    public Long deleteById(Long id) {
        //TODO Validator exists, Exception notFound, not Deleted
        //TODO Relationships with deletion
        return null;
    }
}
