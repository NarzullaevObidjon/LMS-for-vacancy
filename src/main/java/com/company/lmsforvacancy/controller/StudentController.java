package com.company.lmsforvacancy.controller;

import com.company.lmsforvacancy.domain.Faculty;
import com.company.lmsforvacancy.domain.Student;
import com.company.lmsforvacancy.dto.student.StudentCreateDTO;
import com.company.lmsforvacancy.dto.student.StudentUpdateDTO;
import com.company.lmsforvacancy.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/{id}")
    private ResponseEntity<Student> get(@PathVariable Integer id){
        return ResponseEntity.ok(studentService.getStudent(id));
    }

    @PostMapping("/create")
    private ResponseEntity<Student> create(@RequestBody @Valid StudentCreateDTO dto){
        return ResponseEntity.ok(studentService.create(dto));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Integer id){
        return ResponseEntity.ok(studentService.delete(id));
    }

    @PostMapping("/update")
    private ResponseEntity<Student> update(@RequestBody @Valid StudentUpdateDTO dto){
        return ResponseEntity.ok(studentService.update(dto));
    }

    @GetMapping()
    private ResponseEntity<Page<Student>> getAll(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size
    ) {
        return ResponseEntity.ok(studentService.getAll(size, page));
    }
}
