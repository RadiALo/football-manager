package com.radialo.footballmanager.service.mapper;

import com.radialo.footballmanager.dto.request.PlayerRequestDto;
import com.radialo.footballmanager.dto.response.PlayerResponseDto;
import com.radialo.footballmanager.model.Player;
import com.radialo.footballmanager.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerDtoMapper implements RequestDtoMapper<PlayerRequestDto, Player>,
        ResponseDtoMapper<PlayerResponseDto, Player> {
    private final TeamService teamService;

    @Override
    public Player mapToModel(PlayerRequestDto playerResponseDto) {
        Player player = new Player();
        player.setAge(playerResponseDto.getAge());
        player.setFirstName(playerResponseDto.getFirstName());
        player.setLastName(playerResponseDto.getLastName());
        player.setExperienceMonths(playerResponseDto.getExperienceMoths());
        player.setTeam(teamService.get(playerResponseDto.getTeamId()));
        return player;
    }

    @Override
    public PlayerResponseDto mapToDto(Player player) {
        PlayerResponseDto playerResponseDto = new PlayerResponseDto();
        playerResponseDto.setId(player.getId());
        playerResponseDto.setFirstName(player.getFirstName());
        playerResponseDto.setLastName(player.getLastName());
        playerResponseDto.setAge(player.getAge());
        playerResponseDto.setExperienceMonths(player.getExperienceMonths());
        playerResponseDto.setTeamId(player.getTeam() != null ? player.getTeam().getId() : null);
        return playerResponseDto;
    }
}
