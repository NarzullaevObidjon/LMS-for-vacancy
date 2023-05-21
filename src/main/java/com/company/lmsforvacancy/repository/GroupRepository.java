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

public interface GroupRepository extends JpaRepository<Group, Integer> {

    @Transactional
    @Modifying
    @Query("update groups s set s.deleted=true where s.id=:id")
    void delete(Integer id);

    @Query("from groups u where u.id=?1 and not u.deleted")
    Optional<Group> findById(Integer id);

    @Query("from groups u where not u.deleted")
    Page<Group> findAll(Pageable pageable);


    @Query(value = """
                    select 
                        g.id, (
                            select 
                                cast( count(*) as integer )
                            from 
                                Student s where s.group.id=g.id
                        )
                    from 
                        groups g 
                    where g.faculty.id=?1""")
    List<Object[]> findIdsByFaculty(Integer id);

//    @Query("""
//            select
//             *
//            from
//                groups g left join Journal g.id
//            where g.id=?1
//            """)
//    List<Object[]> brbalo(Integer groupId)
}