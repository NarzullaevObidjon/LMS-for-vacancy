package com.company.lmsforvacancy.dto.faculty;

import com.company.lmsforvacancy.dto.group.GroupsStudents;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacultyGroupsDetail {
    private Integer id;
    private String name;
    @JsonProperty("groups_count")
    private Integer groupsCount;
    private List<GroupsStudents> groups;

}
