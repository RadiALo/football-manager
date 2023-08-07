package com.radialo.footballmanager.service;

import com.radialo.footballmanager.model.Player;
import com.radialo.footballmanager.model.Team;

public interface TransferService {
    void transferPlayer(Player player, Team team);
}
