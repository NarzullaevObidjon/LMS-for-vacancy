package com.company.lmsforvacancy.dto.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentsMarkInGroup {
    @JsonProperty("group_id")
    private Integer groupId;

    @JsonProperty("students_mark")
    private List<StudentsMarkInGroup> studentsMark;
}
