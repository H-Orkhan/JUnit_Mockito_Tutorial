package com.junit.mockito.JUnit_Mockito_Tutorial;

import com.junit.mockito.JUnit_Mockito_Tutorial.model.User;
import com.junit.mockito.JUnit_Mockito_Tutorial.repository.UserRepo;
import com.junit.mockito.JUnit_Mockito_Tutorial.services.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.security.RunAs;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class JUnitMockitoTutorialApplicationTests {

	@Autowired
	private UserService service;

	@MockBean
	private UserRepo repository;

	@Test
	public void getUsersTest_v1 () {
		when(repository.findAll()).thenReturn(Stream
				.of(new User(1,"Orkhan","Hashimov","123456789"))
				.collect(Collectors.toList()));
		Assert.assertEquals(1,service.getAllUsers().size());
	}

	@Test
	public void getUsersTest_v2 () {
		when(repository.findAll()).thenReturn(Stream
				.of(new User(1,"Orkhan","Hashimov","123456789"))
				.collect(Collectors.toList()));
		Assert.assertEquals(7,service.getAllUsers().size());
	}


}
