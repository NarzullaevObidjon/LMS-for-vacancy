package com.company.lmsforvacancy.dto.mark;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MarkCreateDTO {
    @Min(value = 0, message = "mark must be between 0 and 5")
    @Max(value = 5,message = "mark must be between 0 and 5")
    private byte mark;
    @JsonProperty("student_id")
    private Integer studentId;
    @JsonProperty("journal_id")
    private Integer journalId;
    @JsonProperty("subject_id")
    private Integer subjectId;
}
