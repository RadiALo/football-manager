package com.radialo.footballmanager.service;

import com.radialo.footballmanager.model.Player;
import java.util.List;
import org.springframework.data.domain.PageRequest;

public interface PlayerService {
    Player add(Player player);

    Player get(Long id);

    List<Player> getAllByPage(PageRequest pageRequest);

    Player update(Player player);

    void delete(Long id);
}
