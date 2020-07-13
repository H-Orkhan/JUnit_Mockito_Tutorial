package com.junit.mockito.JUnit_Mockito_Tutorial.model;

import com.junit.mockito.JUnit_Mockito_Tutorial.repository.UserRepo;
import com.junit.mockito.JUnit_Mockito_Tutorial.services.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {


    @MockBean
    UserRepo userRepo;

    @Autowired
    UserService userService;

    static List<User>userList;

    @BeforeAll
    static void setupPerson () {
        userList = List.of(
                new User (1,"Orkhan","Hashimov","123-456-789"),
                new User (2,"Sabir","Hasanov","987-654-321")
        );
    }

    @Test
    void getId() {
        int expected = userList.get(0).getId();
        int actual = 1;

        Assert.assertEquals(expected,actual);
    }

    @Test
    void getName() {
        String expected = userList.get(0).getName();
        String actual = "Orkhan";
        assertEquals(expected,actual);
    }

    @Test
    void getSurname() {
        String expected = userList.get(0).getSurname();
        String actual = "Hashimov";
        assertEquals(expected,actual);
    }

    @Test
    void getPhone() {
        String expected = userList.get(0).getPhone();
        String actual = "123-456-789";
        assertEquals(expected,actual);
    }
}