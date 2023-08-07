package com.radialo.footballmanager.controller;

import com.radialo.footballmanager.dto.request.PlayerRequestDto;
import com.radialo.footballmanager.dto.response.PlayerResponseDto;
import com.radialo.footballmanager.model.Player;
import com.radialo.footballmanager.service.PlayerService;
import com.radialo.footballmanager.service.TeamService;
import com.radialo.footballmanager.service.TransferService;
import com.radialo.footballmanager.service.mapper.PlayerDtoMapper;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;
    private final PlayerDtoMapper playerDtoMapper;
    private final TeamService teamService;
    private final TransferService transferService;

    @PostMapping
    public PlayerResponseDto add(@Valid @RequestBody PlayerRequestDto dto) {
        return playerDtoMapper
                .mapToDto(playerService.add(playerDtoMapper.mapToModel(dto)));
    }

    @GetMapping("/{id}")
    public PlayerResponseDto get(@PathVariable Long id) {
        Player player = playerService.get(id);
        return playerDtoMapper.mapToDto(player);
    }

    @GetMapping
    public List<PlayerResponseDto> getAllByPage(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return playerService.getAllByPage(pageRequest).stream()
                .map(playerDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public PlayerResponseDto update(@PathVariable Long id,
                                    @Valid @RequestBody PlayerRequestDto dto) {
        Player player = playerDtoMapper.mapToModel(dto);
        player.setId(id);
        return playerDtoMapper.mapToDto(playerService.add(player));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        playerService.delete(id);
    }

    @PostMapping("/{playerId}/transfer")
    public ResponseEntity<Void> transferPlayer(
            @PathVariable Long playerId,
            @RequestParam Long teamId) {
        transferService.transferPlayer(playerService.get(playerId),
                teamService.get(teamId));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/init")
    public List<PlayerResponseDto> initPlayerData(
            @RequestBody List<PlayerRequestDto> playerDtoList) {
        List<PlayerResponseDto> playerResponseDtos = new ArrayList<>();
        for (PlayerRequestDto dto : playerDtoList) {
            playerResponseDtos.add(playerDtoMapper
                    .mapToDto(playerService.add(playerDtoMapper
                            .mapToModel(dto))));
        }
        return playerResponseDtos;
    }
}
