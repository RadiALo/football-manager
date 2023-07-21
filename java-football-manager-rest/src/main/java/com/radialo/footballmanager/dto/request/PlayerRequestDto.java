package com.radialo.footballmanager.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PlayerRequestDto {
    private String firstName;
    private String lastName;
    private LocalDate careerStartDate;
    private int experienceMonths;
    private int age;
    private Long teamId;
}