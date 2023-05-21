package com.company.lmsforvacancy.repository;

import com.company.lmsforvacancy.domain.Journal;
import com.company.lmsforvacancy.domain.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface JournalRepository extends JpaRepository<Journal, Integer> {
    @Transactional
    @Modifying
    @Query("update Journal s set s.deleted=true where s.id=:id")
    void delete(Integer id);

    @Query("from Journal u where u.id=?1 and not u.deleted")
    Optional<Journal> findById(Integer id);

    @Query("from Journal u where not u.deleted")
    Page<Journal> findAll(Pageable pageable);

    @Query("select g.subjects from Journal g where g.id=?1")
    List<Subject> getSubjectsById(Integer id);

    @Query("select g.subjects from Journal g where g.group.id=?1")
    List<Subject> getSubjectsIdsByGroupId(Integer groupId);
}