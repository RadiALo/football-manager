package com.radialo.footballmanager.service.mapper;

import com.radialo.footballmanager.dto.request.TeamRequestDto;
import com.radialo.footballmanager.dto.response.TeamResponseDto;
import com.radialo.footballmanager.model.Team;
import org.springframework.stereotype.Service;

@Service
public class TeamDtoMapper implements RequestDtoMapper<TeamRequestDto, Team>,
        ResponseDtoMapper<TeamResponseDto, Team> {

    @Override
    public Team mapToModel(TeamRequestDto dto) {
        Team team = new Team();
        team.setName(dto.getName());
        team.setCity(dto.getCity());
        team.setCountry(dto.getCountry());
        team.setBudget(dto.getBudget());
        team.setTransferCommission(dto.getTransferCommission());
        return team;
    }

    @Override
    public TeamResponseDto mapToDto(Team team) {
        TeamResponseDto responseDto = new TeamResponseDto();
        responseDto.setId(team.getId());
        responseDto.setName(team.getName());
        responseDto.setCity(team.getCity());
        responseDto.setCountry(team.getCountry());
        responseDto.setBudget(team.getBudget());
        responseDto.setTransferCommission(team.getTransferCommission());
        return responseDto;
    }
}
