package com.radialo.footballmanager.repository;

import com.radialo.footballmanager.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
