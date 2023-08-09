package com.radialo.footballmanager.service.impl;

import com.radialo.footballmanager.model.Player;
import com.radialo.footballmanager.model.Team;
import com.radialo.footballmanager.repository.PlayerRepository;
import com.radialo.footballmanager.repository.TeamRepository;
import com.radialo.footballmanager.service.TeamService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    @Override
    public Team add(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team get(Long id) {
        return teamRepository.getReferenceById(id);
    }

    @Override
    public List<Team> getAllByPage(PageRequest pageRequest) {
        return teamRepository.findAll(pageRequest).toList();
    }

    @Override
    public void delete(Long id) {
        teamRepository.deleteById(id);
    }

    @Override
    public Team update(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public List<Player> getPlayersByTeamId(Long teamId) {
        return playerRepository.findAllByTeamId(teamId);
    }

    @Override
    public Long count() {
        return teamRepository.count();
    }
}
