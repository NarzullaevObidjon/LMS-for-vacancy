package com.company.lmsforvacancy.repository;

import com.company.lmsforvacancy.domain.Journal;
import com.company.lmsforvacancy.domain.Mark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MarkRepository extends JpaRepository<Mark, Integer> {
    @Transactional
    @Modifying
    @Query("update Mark s set s.deleted=true where s.id=:id")
    void delete(Integer id);

    @Query("from Mark u where u.id=?1 and not u.deleted")
    Optional<Mark > findById(Integer id);

    @Query("from Mark u where not u.deleted")
    Page<Mark> findAll(Pageable pageable);
}