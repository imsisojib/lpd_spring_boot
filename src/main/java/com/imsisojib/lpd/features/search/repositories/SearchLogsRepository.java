package com.imsisojib.lpd.features.search.repositories;

import com.imsisojib.lpd.features.search.models.entities.SearchLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchLogsRepository extends JpaRepository<SearchLogs, Long> {
}
