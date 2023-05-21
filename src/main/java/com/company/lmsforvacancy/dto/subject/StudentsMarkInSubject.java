package com.company.lmsforvacancy.dto.subject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentsMarkInSubject {
    @JsonProperty("subject_id")
    private Integer subjectId;
    @JsonProperty("subject_name")
    private String subjectName;
    @JsonProperty("grades")
    private List<StudentNameAndSumOfMarkBySubject> grades;


}
