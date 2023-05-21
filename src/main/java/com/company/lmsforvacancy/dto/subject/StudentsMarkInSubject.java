package com.company.lmsforvacancy.dto.subject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class StudentsMarkInSubject {
    @JsonProperty("subject_name")
    private Integer subjectName;
    @JsonProperty("subject_id")
    private String subjectId;
    private Map<String, Integer> grades;
}
