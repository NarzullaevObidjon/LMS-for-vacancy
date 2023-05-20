package com.company.lmsforvacancy.dto.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class GroupCreateDTO {
    @NotBlank(message = "name cannot be blank")
    private String name;
    @JsonProperty("faculty_id")
    private Integer facultyId;
    @Min(value = 0, message = "year must be between 0 and 6")
    @Max(value = 6, message ="year must be between 0 and 6")
    private Short year;
}
