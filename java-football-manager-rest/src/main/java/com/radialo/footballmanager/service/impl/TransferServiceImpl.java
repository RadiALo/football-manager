package com.radialo.footballmanager.service.impl;

import com.radialo.footballmanager.exception.TransferException;
import com.radialo.footballmanager.model.Player;
import com.radialo.footballmanager.model.Team;
import com.radialo.footballmanager.service.PlayerService;
import com.radialo.footballmanager.service.TeamService;
import com.radialo.footballmanager.service.TransferService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {
    private static final BigDecimal HUNDRED = BigDecimal.valueOf(100);
    private static final BigDecimal EXPERIENCE_MULTIPLIER = BigDecimal.valueOf(100000);
    private final PlayerService playerService;
    private final TeamService teamService;

    @Override
    public void transferPlayer(Player player, Team team) {
        Team oldTeam = player.getTeam();
        if (player == null) {
            throw new TransferException("Player to transfer cannot be null!");
        } else if (team == null) {
            throw new TransferException("Team to transfer cannot be null!");
        } else if (team.equals(oldTeam)) {
            throw new TransferException("Player cannot be transferred to own team!");
        } else {
            BigDecimal price = getTransferPrice(player, team);
            if (team.getBudget().compareTo(price) < 0) {
                throw new RuntimeException("Team has no enough budget to transfer!");
            }
            transfer(player, team, price);
        }
    }

    private void transfer(Player player, Team team, BigDecimal price) {
        Team oldTeam = player.getTeam();
        team.setBudget(team.getBudget().subtract(price));
        oldTeam.setBudget(oldTeam.getBudget().add(price));
        player.setTeam(team);
        teamService.add(team);
        teamService.add(oldTeam);
        playerService.add(player);
    }

    private BigDecimal getTransferPrice(Player player, Team newTeam) {
        BigDecimal experienceMonths = BigDecimal.valueOf(player.getExperienceMonths());
        BigDecimal playerAge = BigDecimal.valueOf(player.getAge());
        BigDecimal basePrice = experienceMonths
                .multiply(EXPERIENCE_MULTIPLIER)
                .divide(playerAge, RoundingMode.HALF_UP);
        BigDecimal commissionRate = newTeam
                .getTransferCommission()
                .divide(HUNDRED, RoundingMode.HALF_UP);
        BigDecimal commission = basePrice.multiply(commissionRate);
        return basePrice.add(commission);
    }
}
