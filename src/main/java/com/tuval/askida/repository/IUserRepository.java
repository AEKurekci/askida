package com.tuval.askida.repository;

import com.tuval.askida.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<Owner, Long> {

    Optional<Owner> findByEmail(String email);
}
