package com.company.lmsforvacancy.dto.student;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StudentUpdateDTO {
    @NotBlank(message = "name cannot be blank")
    private String name;
    @Positive(message = "id must be positive")
    private Integer id;
}
