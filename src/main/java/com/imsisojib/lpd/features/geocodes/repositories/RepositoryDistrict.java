package com.imsisojib.lpd.features.geocodes.repositories;

import com.imsisojib.lpd.features.geocodes.models.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryDistrict extends JpaRepository<District, String> {

}
