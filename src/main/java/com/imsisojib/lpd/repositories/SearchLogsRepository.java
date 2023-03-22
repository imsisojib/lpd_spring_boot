package com.imsisojib.lpd.repositories;

import com.imsisojib.lpd.models.entities.SearchLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchLogsRepository extends JpaRepository<SearchLogs, Long> {
}
