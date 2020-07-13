package com.junit.mockito.JUnit_Mockito_Tutorial.services;

import com.junit.mockito.JUnit_Mockito_Tutorial.model.User;
import com.junit.mockito.JUnit_Mockito_Tutorial.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo repository;

    public List<User> getAllUsers() {
        return  repository.findAll();
    }

    public User getUserByID(int id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public User addUser(User user)  {
        if (!checkIfExists(user)) return repository.save(user);
        throw new IllegalAccessError("User Is already exists");
    }

    public void deleteUser(User user) { repository.delete(user);
    }

    public boolean checkIfExists (User user) {
        return getAllUsers().contains(user);
    }
}
