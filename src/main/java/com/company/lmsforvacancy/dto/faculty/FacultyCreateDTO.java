package com.company.lmsforvacancy.dto.faculty;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacultyCreateDTO {
    @NotBlank(message = "name cannot be blank")
    private String name;

    @JsonProperty("university_id")
    private Integer universityId;
}
