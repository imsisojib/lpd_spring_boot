package com.imsisojib.lpd.features.geocodes.services;

import com.imsisojib.lpd.features.geocodes.models.entities.District;
import com.imsisojib.lpd.features.geocodes.models.entities.Division;
import com.imsisojib.lpd.features.geocodes.models.entities.Upazila;
import com.imsisojib.lpd.features.geocodes.repositories.RepositoryDistrict;
import com.imsisojib.lpd.features.geocodes.repositories.RepositoryDivision;
import com.imsisojib.lpd.features.geocodes.repositories.RepositoryUpazila;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceGeocodes {
    @Autowired
    RepositoryDivision repositoryDivision;
    @Autowired
    RepositoryDistrict repositoryDistrict;
    @Autowired
    RepositoryUpazila repositoryUpazila;

    public List<Division> findAllDivisions(){
        return repositoryDivision.findAll();
    }

    public List<District> findAllDistricts(){
        return repositoryDistrict.findAll();
    }

    public List<Upazila> findAllUpazilas(){
        return repositoryUpazila.findAll();
    }

    public List<Upazila> findUpazilasByDistrictId(Long districtId){
        return repositoryUpazila.findUpazilasByDistrictId(districtId);
    }

    public List<District> findDistrictsByDivisionId(Long divisionId){
        return repositoryDistrict.findDistrictsByDivisionId(divisionId);
    }
}
