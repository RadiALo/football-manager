package com.radialo.footballmanager.dto.response;

import java.time.LocalDate;
import lombok.Data;

@Data
public class PlayerResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate careerStartDate;
    private int experienceMonths;
    private int age;
    private Long teamId;
}