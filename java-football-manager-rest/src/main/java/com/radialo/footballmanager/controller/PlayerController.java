package com.radialo.footballmanager.controller;

import com.radialo.footballmanager.dto.request.PlayerRequestDto;
import com.radialo.footballmanager.dto.response.PlayerResponseDto;
import com.radialo.footballmanager.model.Player;
import com.radialo.footballmanager.service.PlayerService;
import com.radialo.footballmanager.service.mapper.PlayerDtoMapper;
import jakarta.validation.Valid;
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

    @PostMapping("/init")
    public ResponseEntity<String> initPlayerData(
            @RequestBody List<PlayerRequestDto> playerDtoList) {
        for (PlayerRequestDto dto : playerDtoList) {
            playerService.add(playerDtoMapper.mapToModel(dto));
        }
        return ResponseEntity.ok("Data initialized successfully for players");
    }
}
