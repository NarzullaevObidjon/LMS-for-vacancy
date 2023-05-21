package com.company.lmsforvacancy.dto.student;


import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StudentCreateDTO {
    @NotBlank(message = "name cannot be blank")
    private String name;
    @JsonAlias("group_id")
    @Positive(message = "id must be positive")
    private Integer groupId;
    @Size(min = 3, max = 20)
    private String username;
    @Size(min = 3, max = 20)
    private String password;
}
