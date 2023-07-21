package com.radialo.footballmanager.repository;

import com.radialo.footballmanager.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
