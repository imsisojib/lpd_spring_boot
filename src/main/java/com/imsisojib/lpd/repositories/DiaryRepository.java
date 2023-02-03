package com.imsisojib.lpd.repositories;

import com.imsisojib.lpd.models.entities.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, String> {

}
