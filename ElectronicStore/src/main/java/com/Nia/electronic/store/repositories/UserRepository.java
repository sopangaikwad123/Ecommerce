package com.Nia.electronic.store.repositories;


import com.Nia.electronic.store.controllers.UserController;
import com.Nia.electronic.store.entites.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {


Optional<User> findByEmail(String email);

Optional<User> findByEmailAndPassword(String Email ,String password);

List<User> findByNameContaining(String keywords);
}

