package com.imsisojib.lpd.features.lost_diary.repositories;

import com.imsisojib.lpd.features.lost_diary.models.entities.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, String> {
    Optional<Diary> findDiaryByEmi(String emi);
}
