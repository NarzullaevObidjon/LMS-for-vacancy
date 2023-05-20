package com.company.lmsforvacancy.repository;

import com.company.lmsforvacancy.domain.Faculty;
import com.company.lmsforvacancy.domain.University;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
    @Transactional
    @Modifying
    @Query("update Faculty s set s.deleted=true where s.id=:id")
    void delete(Integer id);

    @Query("from Faculty u where u.id=?1 and not u.deleted")
    Optional<Faculty> findById(Integer id);

    @Query("from Faculty u where not u.deleted")
    Page<Faculty> findAll(Pageable pageable);
}