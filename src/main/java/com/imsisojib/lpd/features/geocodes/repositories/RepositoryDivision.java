package com.imsisojib.lpd.features.geocodes.repositories;

import com.imsisojib.lpd.features.geocodes.models.entities.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryDivision extends JpaRepository<Division, String> {

}
