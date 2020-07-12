package com.junit.mockito.JUnit_Mockito_Tutorial.services;

import com.junit.mockito.JUnit_Mockito_Tutorial.model.User;
import com.junit.mockito.JUnit_Mockito_Tutorial.repository.UserRepo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService service;

    @MockBean
    private UserRepo repo;


    @Test
    void getAllUsers_v1() {
        when(repo.findAll()).thenReturn(
                Stream.of(
                        new User(1,"Orkhan","Hashimov","123-456-789"),
                        new User(2,"Sabir","Hasanov","987-654-321"))
                        .collect(Collectors.toList())
        );

        Assert.assertEquals(2,service.getAllUsers().size());
    }

    @Test
    void getUserByID() {
        List<User> userList = new ArrayList<>();
        int id = 1;
        userList.add( new User(1,"Orkhan","Hashimov","123-456-789"));
        userList.add( new User(2,"Sabir","Hasanov","987-654-321"));

        when(repo.findById(id)).thenReturn(Optional.ofNullable(userList.get(1)));

        Assert.assertEquals(service.getUserByID(id),userList.get(id));
    }

    @Test
    void addUser() {

        User user = new User (1,"Orkhan","Hashimov","123-456-789");
        when(service.addUser(user)).thenReturn(user);
        Assert.assertEquals(user,service.addUser(user));

    }

    @Test
    void deleteUser() {
        User user = new User (1,"Orkhan","Hashimov","123-456-789");
        service.deleteUser(user);
        verify(repo,times(1)).delete(user);
    }
}