package com.redisaop.service;


import com.redisaop.aspect.CacheTTL;
import com.redisaop.model.User;
import com.redisaop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository){

        this.userRepository = userRepository;

    }
    @Override
    public User getById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    @CacheTTL(ttlMinutes = 5)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User Add(User user) {

        userRepository.save(user);
        return user;

    }

    @Override
    public void Update(int id,User user) {
        User oldUser = userRepository.getById(id);
        oldUser.setEmail(user.getEmail());
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setDateOfBirth(user.getDateOfBirth());
        oldUser.setPhone(user.getPhone());
        oldUser.setNationalityId(user.getNationalityId());
        userRepository.save(oldUser);

    }

    @Override
    public void Delete(int id) {
        User user = userRepository.getById(id);
        userRepository.delete(user);

    }


}
