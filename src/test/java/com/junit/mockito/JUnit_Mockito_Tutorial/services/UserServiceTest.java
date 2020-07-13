package com.junit.mockito.JUnit_Mockito_Tutorial.services;

import com.junit.mockito.JUnit_Mockito_Tutorial.model.User;
import com.junit.mockito.JUnit_Mockito_Tutorial.repository.UserRepo;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserServiceTest {

    @InjectMocks
    private UserService service;

    @MockBean
    private UserRepo repo;

    @Autowired
    private static List<User> userList;

    @BeforeAll
    static void setup() {
        userList = List.of (
                new User(0, "Somebody", "Somebody", "000-000-000"),
                new User(1, "Orkhan", "Hashimov", "123-456-789"),
                new User(25, "Sabir", "Hasanov", "987-654-321")
        );
    }

    @Test
    void getAllUsers() {
        when(repo.findAll()).thenReturn(userList);
        Assert.assertEquals(3, service.getAllUsers().size());
        Assert.assertNotNull(service.getAllUsers());
    }

    @Test
    void getUserByID() {
        int id = 1;
        when(repo.findById(id)).
                thenReturn(Optional.ofNullable(userList.stream().filter(i -> i.getId() == id).findAny().orElse(null)));
        assertThat(service.getUserByID(id)).isNotNull();
        verify(repo).findById(id);
    }

    @Test
    void addUser() {
        User user = userList.get(1);
        when(repo.save(user)).thenReturn(user);
        assertThat(service.addUser(user)).isNotNull();
        verify(repo).save(user);
    }

    @Test
    void deleteUser() {
        User user = new User(1, "Orkhan", "Hashimov", "123-456-789");
        service.deleteUser(user);
        verify(repo, times(1)).delete(user);
    }
}