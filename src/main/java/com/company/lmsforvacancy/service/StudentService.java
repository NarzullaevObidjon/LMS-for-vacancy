package com.company.lmsforvacancy.service;

import com.company.lmsforvacancy.domain.Group;
import com.company.lmsforvacancy.domain.Student;
import com.company.lmsforvacancy.dto.student.StudentCreateDTO;
import com.company.lmsforvacancy.dto.student.StudentUpdateDTO;
import com.company.lmsforvacancy.repository.StudentRepository;
import com.company.lmsforvacancy.exceptions.ItemNotFoundException;
import com.company.lmsforvacancy.domain.AuthUser;
import com.company.lmsforvacancy.repository.AuthUserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "student")
public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupService groupService;
    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Cacheable(key = "#id")
    public Student getStudent(@NonNull Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("User not found with id : " + id));
    }

    public Student create(StudentCreateDTO dto) {
        Group group = groupService.get(dto.getGroupId());

        AuthUser savedUser = authUserRepository.save(
                AuthUser.builder()
                        .username(dto.getUsername())
                        .password(passwordEncoder.encode(dto.getPassword()))
                        .role(AuthUser.Role.STUDENT)
                        .build());

        return studentRepository.save(
                Student.builder()
                        .authUserId(savedUser.getId())
                        .name(dto.getName())
                        .group(group)
                        .build());
    }

    @CacheEvict(key = "#id")
    public Boolean delete(@NonNull Integer id) {
        Student student = getStudent(id);
        studentRepository.delete(id);
        authUserRepository.delete(student.getAuthUserId());
        return true;
    }

    @CachePut(key = "#result.id")
    public Student update(StudentUpdateDTO dto) {
        Student student = getStudent(dto.getId());
        Group group = groupService.get(dto.getGroupId());
        student.setName(dto.getName());
        student.setGroup(group);
        studentRepository.save(student);
        return student;
    }

    public Page<Student> getAll(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.findAll(pageable);
    }

    public List<Student> getStudentsWithName(String name) {
        return studentRepository.findAllByNameEqualsAndDeleted(name, false);
    }
}
