package com.radialo.footballmanager.dto.response;

import lombok.Data;

@Data
public class PlayerResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private int experienceMonths;
    private int age;
    private Long teamId;
}
