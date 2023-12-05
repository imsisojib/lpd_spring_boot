package com.imsisojib.lpd.features.account.repositories;

import com.imsisojib.lpd.features.account.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
