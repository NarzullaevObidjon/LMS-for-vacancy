package com.company.lmsforvacancy.dto.journal;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JournalCreateDTO {
    @NotBlank(message = "name cannot be blank")
    private String name;
    @JsonProperty("group_id")
    private Integer groupId;
}
