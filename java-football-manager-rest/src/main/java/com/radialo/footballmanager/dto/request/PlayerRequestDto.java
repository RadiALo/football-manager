package com.radialo.footballmanager.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class PlayerRequestDto {
    private String firstName;
    private String lastName;
    private int experienceMonths;
    @Min(value = 18, message = "Players age cannot be less than 18")
    private int age;
    private Long teamId;
}
