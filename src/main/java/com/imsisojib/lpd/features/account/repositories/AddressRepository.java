package com.imsisojib.lpd.features.account.repositories;

import com.imsisojib.lpd.features.geocodes.models.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
