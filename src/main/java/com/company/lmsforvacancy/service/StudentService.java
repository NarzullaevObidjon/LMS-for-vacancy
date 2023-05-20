package com.company.lmsforvacancy.service;

import com.company.lmsforvacancy.domain.Group;
import com.company.lmsforvacancy.domain.Student;
import com.company.lmsforvacancy.dto.student.StudentCreateDTO;
import com.company.lmsforvacancy.dto.student.StudentUpdateDTO;
import com.company.lmsforvacancy.repository.GroupRepository;
import com.company.lmsforvacancy.repository.StudentRepository;
import com.company.lmsforvacancy.exceptions.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public Student getStudent(Integer id) {
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

    public Boolean delete(Integer id) {
         studentRepository.delete(id);
         return true;
    }

    public Student update(StudentUpdateDTO dto) {
        return studentRepository.update(dto.getId(), dto.getName());
    }
}
