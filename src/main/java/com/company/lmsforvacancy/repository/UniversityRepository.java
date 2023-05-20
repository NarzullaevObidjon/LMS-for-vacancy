package com.company.lmsforvacancy.repository;

import com.company.lmsforvacancy.domain.University;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {

    @Transactional
    @Modifying
    @Query("update University s set s.deleted=true where s.id=:id")
    void delete(Integer id);

    @Query("from University u where u.id=?1 and not u.deleted")
    Optional<University> findById(Integer id);

    @Query("from University u where not u.deleted")
    Page<University> findAll(Pageable pageable);
}