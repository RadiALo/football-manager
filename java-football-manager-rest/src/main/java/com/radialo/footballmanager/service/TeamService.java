package com.radialo.footballmanager.service;

import com.radialo.footballmanager.model.Player;
import com.radialo.footballmanager.model.Team;
import java.util.List;
import org.springframework.data.domain.PageRequest;

public interface TeamService {
    Team add(Team team);

    Team get(Long id);

    List<Team> getAllByPage(PageRequest pageRequest);

    void delete(Long id);

    Team update(Team team);

    List<Player> getPlayersByTeamId(Long teamId);
}
