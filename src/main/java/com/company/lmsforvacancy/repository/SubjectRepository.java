package com.company.lmsforvacancy.repository;

import com.company.lmsforvacancy.domain.Group;
import com.company.lmsforvacancy.domain.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    @Transactional
    @Modifying
    @Query("update Subject s set s.deleted=true where s.id=:id")
    void delete(Integer id);

    @Query("from Subject u where u.id=?1 and not u.deleted")
    Optional<Subject> findById(Integer id);

    @Query("from Subject u where not u.deleted")
    Page<Subject> findAll(Pageable pageable);

}