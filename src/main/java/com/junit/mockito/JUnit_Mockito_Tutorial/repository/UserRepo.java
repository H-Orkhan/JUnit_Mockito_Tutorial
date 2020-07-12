package com.junit.mockito.JUnit_Mockito_Tutorial.repository;

import com.junit.mockito.JUnit_Mockito_Tutorial.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
}
