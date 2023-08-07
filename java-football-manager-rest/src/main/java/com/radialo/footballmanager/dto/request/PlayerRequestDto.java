package com.radialo.footballmanager.dto.request;

import lombok.Data;

@Data
public class PlayerRequestDto {
    private String firstName;
    private String lastName;
    private int experienceMoths;
    private int age;
    private Long teamId;
}
