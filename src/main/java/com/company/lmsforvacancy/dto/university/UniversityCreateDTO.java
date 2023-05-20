package com.company.lmsforvacancy.dto.university;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UniversityCreateDTO {
    @NotBlank(message = "name cannot be blank")
    private String name;
    @NotBlank(message = "address cannot be blank")
    private String address;
    @JsonProperty("open_year")
    @Positive(message = "open_year cannot be negative")
    private Short openYear;
}
