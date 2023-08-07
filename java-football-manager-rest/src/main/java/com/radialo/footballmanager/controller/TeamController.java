package com.radialo.footballmanager.controller;

import com.radialo.footballmanager.dto.request.TeamRequestDto;
import com.radialo.footballmanager.dto.response.PlayerResponseDto;
import com.radialo.footballmanager.dto.response.TeamResponseDto;
import com.radialo.footballmanager.model.Team;
import com.radialo.footballmanager.service.TeamService;
import com.radialo.footballmanager.service.mapper.PlayerDtoMapper;
import com.radialo.footballmanager.service.mapper.TeamDtoMapper;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;
    private final TeamDtoMapper teamDtoMapper;
    private final PlayerDtoMapper playerDtoMapper;

    @PostMapping
    public TeamResponseDto create(@Valid @RequestBody TeamRequestDto dto) {
        return teamDtoMapper
                .mapToDto(teamService.add(teamDtoMapper.mapToModel(dto)));
    }

    @GetMapping
    public TeamResponseDto get(@PathVariable Long id) {
        return teamDtoMapper.mapToDto(teamService.get(id));
    }

    @PutMapping
    public TeamResponseDto update(@PathVariable Long id,
                                  @Valid @RequestBody TeamRequestDto dto) {
        Team team = teamDtoMapper.mapToModel(dto);
        team.setId(id);
        return teamDtoMapper.mapToDto(teamService.add(team));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        teamService.delete(id);
    }

    @GetMapping
    public List<TeamResponseDto> getAllByPage(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return teamService.getAllByPage(pageRequest).stream()
                .map(teamDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/init")
    public List<TeamResponseDto> initTeamData(
            @RequestBody List<TeamRequestDto> teamDtoList) {
        List<TeamResponseDto> playerResponseDtos = new ArrayList<>();
        for (TeamRequestDto dto : teamDtoList) {
            playerResponseDtos.add(teamDtoMapper
                    .mapToDto(teamService.add(teamDtoMapper
                            .mapToModel(dto))));
        }
        return playerResponseDtos;
    }

    @GetMapping("/{teamId}/players")
    public List<PlayerResponseDto> getPlayersByTeamId(@PathVariable Long teamId) {
        return teamService.getPlayersByTeamId(teamId).stream()
                .map(playerDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
