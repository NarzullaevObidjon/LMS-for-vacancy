package com.company.lmsforvacancy.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUser {
    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean deleted;

    public enum Role {
        STUDENT, ADMIN
    }

}
