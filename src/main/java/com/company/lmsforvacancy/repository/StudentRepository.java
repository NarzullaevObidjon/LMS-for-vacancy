package com.company.lmsforvacancy.repository;

import com.company.lmsforvacancy.domain.Group;
import com.company.lmsforvacancy.domain.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Transactional
    @Modifying
    @Query("update Student s set s.deleted=true where s.id=?1")
    void delete(Integer id);

    @Query("from Student u where u.id=?1 and not u.deleted")
    Optional<Student> findById(Integer id);

    @Query("from Student u where not u.deleted")
    Page<Student> findAll(Pageable pageable);

    List<Student> findAllByNameEqualsAndDeleted(String name, boolean deleted);
}