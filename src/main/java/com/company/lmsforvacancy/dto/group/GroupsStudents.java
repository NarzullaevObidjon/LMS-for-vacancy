package com.company.lmsforvacancy.dto.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class GroupsStudents implements Serializable {
    @JsonProperty("group_id")
    private Integer groupId;
    @JsonProperty("students_count")
    private Integer studentsCount;

    public GroupsStudents(Integer groupId, Integer studentsCount) {
        this.groupId = groupId;
        this.studentsCount = studentsCount;
    }
}