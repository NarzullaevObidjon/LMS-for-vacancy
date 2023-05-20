package com.company.lmsforvacancy.repository;

import com.company.lmsforvacancy.domain.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Modifying
    @Transactional
    @Query("update Student s set s.name=?2 where s.id=?1")
    Student update(Integer id, String name);

    @Query("update Student s set s.deleted=true where s.id=:id")
    void delete(Integer id);
}