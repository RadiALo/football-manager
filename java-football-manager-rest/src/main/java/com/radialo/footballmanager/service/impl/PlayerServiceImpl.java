package com.radialo.footballmanager.service.impl;

import com.radialo.footballmanager.model.Player;
import com.radialo.footballmanager.repository.PlayerRepository;
import com.radialo.footballmanager.service.PlayerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public Player add(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player get(Long id) {
        return playerRepository.getReferenceById(id);
    }

    @Override
    public List<Player> getAllByPage(PageRequest pageRequest) {
        return playerRepository.findAll(pageRequest).toList();
    }

    @Override
    public Player update(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public void delete(Long id) {
        playerRepository.deleteById(id);
    }
}
