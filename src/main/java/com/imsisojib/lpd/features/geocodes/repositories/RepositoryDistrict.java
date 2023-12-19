package com.imsisojib.lpd.features.geocodes.repositories;

import com.imsisojib.lpd.features.geocodes.models.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryDistrict extends JpaRepository<District, String> {
    public Optional<District> findById(Long divisionId);
    public List<District> findDistrictsByDivisionId(Long divisionId);
}
