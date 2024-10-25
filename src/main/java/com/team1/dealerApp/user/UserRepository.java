package com.team1.dealerApp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository< User, UUID > {
	Optional<User> findByEmail( String email );

	boolean existsByEmail( String email );

	void deleteByEmail(String email);

}
