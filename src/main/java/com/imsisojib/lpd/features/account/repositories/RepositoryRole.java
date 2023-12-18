package com.imsisojib.lpd.features.account.repositories;

import com.imsisojib.lpd.features.account.enums.ERole;
import com.imsisojib.lpd.features.account.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryRole extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
