package com.company.lmsforvacancy.dto.university;

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
public class UniversityUpdateDTO {
    private Integer id;
    @NotBlank(message = "name cannot be blank")
    private String name;
    @NotBlank(message = "address cannot be blank")
    private String address;
    @JsonProperty("open_year")
    @Positive(message = "open_year cannot be negative")
    private short openYear;
}
