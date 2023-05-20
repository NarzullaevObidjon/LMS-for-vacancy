package com.company.lmsforvacancy.dto.student;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private Integer id;
    @NotNull
    @JsonProperty("group_id")
    private Integer groupId;

}
