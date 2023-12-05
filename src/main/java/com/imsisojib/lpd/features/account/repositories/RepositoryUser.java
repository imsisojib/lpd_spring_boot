package com.imsisojib.lpd.features.account.repositories;

import com.imsisojib.lpd.features.account.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryUser extends JpaRepository<User, String> {
    public Optional<User> findUserByEmail(String email);

    public Optional<User> findUserById(Long id);
}
