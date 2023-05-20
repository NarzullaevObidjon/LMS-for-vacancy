package com.company.lmsforvacancy.controller;

import com.company.lmsforvacancy.domain.Faculty;
import com.company.lmsforvacancy.domain.University;
import com.company.lmsforvacancy.dto.faculty.FacultyCreateDTO;
import com.company.lmsforvacancy.dto.university.UniversityCreateDTO;
import com.company.lmsforvacancy.dto.university.UniversityUpdateDTO;
import com.company.lmsforvacancy.service.FacultyService;
import com.company.lmsforvacancy.service.UniversityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/faculty")
@RequiredArgsConstructor
public class FacultyController {
    private final FacultyService facultyService;

    @GetMapping("/{id}")
    private ResponseEntity<Faculty> get(@PathVariable Integer id) {
        return ResponseEntity.ok(facultyService.get(id));
    }

    @GetMapping()
    private ResponseEntity<Page<Faculty>> getAll(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size
    ) {
        return ResponseEntity.ok(facultyService.getAll(size, page));
    }

    @PostMapping("/create")
    private ResponseEntity<Faculty> create(@Valid @RequestBody FacultyCreateDTO dto) {
        return ResponseEntity.ok(facultyService.create(dto));
    }

    @DeleteMapping("/delete/{id}")

    private ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(facultyService.delete(id));
    }

    @PostMapping("/update")
    private ResponseEntity<Faculty> update(@RequestParam("id") Integer id,@RequestParam("name") String name) {
        return ResponseEntity.ok(facultyService.update(id, name));
    }

}
