package com.company.lmsforvacancy.controller;

import com.company.lmsforvacancy.domain.Subject;
import com.company.lmsforvacancy.service.GroupService;
import com.company.lmsforvacancy.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subject")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class SubjectController {
    private final SubjectService subjectService;

    @GetMapping("/{id}")
    private ResponseEntity<Subject> get(@PathVariable Integer id) {
        return ResponseEntity.ok(subjectService.get(id));
    }

    @GetMapping()
    private ResponseEntity<Page<Subject>> getAll(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size
    ) {
        return ResponseEntity.ok(subjectService.getAll(size, page));
    }

    @PostMapping("/create")
    private ResponseEntity<Subject> create(@RequestParam("name") String name) {
        return ResponseEntity.ok(subjectService.create(name));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(subjectService.delete(id));
    }

    @PostMapping("/update")
    private ResponseEntity<Subject> update(
            @RequestParam("name") String name,
            @RequestParam("id") Integer id)
    {
        return ResponseEntity.ok(subjectService.update(name,id));
    }
}
