package com.imsisojib.lpd.features.geocodes.repositories;

import com.imsisojib.lpd.features.geocodes.models.entities.Upazila;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUpazila extends JpaRepository<Upazila, String> {

}
