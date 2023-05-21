package com.company.lmsforvacancy.controller;

import com.company.lmsforvacancy.domain.Faculty;
import com.company.lmsforvacancy.domain.Student;
import com.company.lmsforvacancy.domain.Subject;
import com.company.lmsforvacancy.dto.student.StudentCreateDTO;
import com.company.lmsforvacancy.dto.student.StudentUpdateDTO;
import com.company.lmsforvacancy.service.GroupService;
import com.company.lmsforvacancy.service.JournalService;
import com.company.lmsforvacancy.service.StudentService;
import com.company.lmsforvacancy.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final JournalService journalService;

    //crud

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    private ResponseEntity<Student> get(@PathVariable Integer id){
        return ResponseEntity.ok(studentService.getStudent(id));
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN')")
    private ResponseEntity<Student> create(@RequestBody @Valid StudentCreateDTO dto){
        return ResponseEntity.ok(studentService.create(dto));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<Boolean> delete(@PathVariable Integer id){
        return ResponseEntity.ok(studentService.delete(id));
    }

    @PostMapping("/update")
    @PreAuthorize("isAuthenticated()")
    private ResponseEntity<Student> update(@RequestBody @Valid StudentUpdateDTO dto){
        return ResponseEntity.ok(studentService.update(dto));
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<Page<Student>> getAll(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size
    ) {
        return ResponseEntity.ok(studentService.getAll(size, page));
    }

    // additional

    @GetMapping("/subjects/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Subject>> getSubjects(@PathVariable Integer id){
        return ResponseEntity.ok(journalService.getSubjects(id));
    }

    @GetMapping("/search/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<List<Student>> searchWithName(@PathVariable String name){
        return ResponseEntity.ok(studentService.getStudentsWithName(name));
    }
}
