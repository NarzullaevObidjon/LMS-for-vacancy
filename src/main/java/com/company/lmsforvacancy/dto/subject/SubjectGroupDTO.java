package com.company.lmsforvacancy.dto.subject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectGroupDTO {
    @JsonProperty("subject_id")
    private Integer subjectId;
    @JsonProperty("group_id")
    private Integer groupId;
}
