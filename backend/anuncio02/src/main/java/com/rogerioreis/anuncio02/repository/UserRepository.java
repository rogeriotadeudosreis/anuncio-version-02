package com.rogerioreis.anuncio02.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rogerioreis.anuncio02.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	//@Query("SELECT u FROM User u WHERE u.email=:email")
	Optional<User> findByEmail(String email);

	List<User> findByName(String name);
	
}
