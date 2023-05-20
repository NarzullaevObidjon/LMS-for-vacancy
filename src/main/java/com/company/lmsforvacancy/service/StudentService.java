package com.company.lmsforvacancy.service;

import com.company.lmsforvacancy.domain.Group;
import com.company.lmsforvacancy.domain.Student;
import com.company.lmsforvacancy.dto.student.StudentCreateDTO;
import com.company.lmsforvacancy.dto.student.StudentUpdateDTO;
import com.company.lmsforvacancy.repository.GroupRepository;
import com.company.lmsforvacancy.repository.StudentRepository;
import com.company.lmsforvacancy.exceptions.ItemNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "student")
public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    @Cacheable(key = "#id")
    public Student getStudent(@NonNull Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("User not found with id : " + id));
    }

    public Student create(StudentCreateDTO dto) {
        Group group = groupRepository.findById(dto.getGroupId())
                .orElseThrow(() -> new ItemNotFoundException("Group not found with id : " + dto.getGroupId()));

        return studentRepository.save(
                Student.builder()
                        .name(dto.getName())
                        .group(group)
                        .build());
    }

    @CacheEvict(key = "#id")
    public Boolean delete(@NonNull Integer id) {
         studentRepository.delete(id);
         return true;
    }

    @CachePut(key = "#result.id")
    public Student update(StudentUpdateDTO dto) {
        Student student = studentRepository.findById(dto.getId())
                .orElseThrow(() -> new ItemNotFoundException("Student not found with id : " + dto.getId()));

        Group group = groupRepository.findById(dto.getId())
                .orElseThrow(() -> new ItemNotFoundException("Group not found with id : " + dto.getId()));

        student.setName(dto.getName());
        student.setGroup(group);
        studentRepository.save(student);
        return student;
    }

    public Page<Student> getAll(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.findAll(pageable);
    }
}
