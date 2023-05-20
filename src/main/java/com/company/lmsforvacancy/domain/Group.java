package com.company.lmsforvacancy.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne
    private Faculty faculty;
    private short year;
    @ManyToMany
    @Builder.Default
    private List<Subject> subjects = new ArrayList<>();
    private boolean deleted;
}

