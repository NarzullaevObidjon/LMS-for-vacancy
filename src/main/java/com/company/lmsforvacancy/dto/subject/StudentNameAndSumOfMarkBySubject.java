package com.company.lmsforvacancy.dto.subject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StudentNameAndSumOfMarkBySubject {
    @JsonProperty("name")
    private String studentName;
    @JsonProperty("total_mark")
    private Integer totalMark;
}
