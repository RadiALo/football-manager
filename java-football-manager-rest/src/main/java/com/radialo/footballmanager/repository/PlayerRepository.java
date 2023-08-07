package com.radialo.footballmanager.repository;

import com.radialo.footballmanager.model.Player;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findAllByTeamId(Long teamId);
}
