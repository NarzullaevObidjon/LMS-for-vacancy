package com.company.lmsforvacancy.repository;

import com.company.lmsforvacancy.domain.AuthUser;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    @Query("select a from AuthUser a where upper(a.username) = upper(?1)")
    Optional<AuthUser> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("update AuthUser s set s.deleted=true where s.id=?1")
    void delete(Integer id);
}