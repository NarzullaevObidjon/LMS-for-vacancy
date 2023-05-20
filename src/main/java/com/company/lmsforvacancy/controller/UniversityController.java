package com.company.lmsforvacancy.controller;

import com.company.lmsforvacancy.domain.University;
import com.company.lmsforvacancy.dto.university.UniversityCreateDTO;
import com.company.lmsforvacancy.dto.university.UniversityUpdateDTO;
import com.company.lmsforvacancy.service.UniversityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/university")
@RequiredArgsConstructor
public class UniversityController {
    private final UniversityService universityService;

    @GetMapping("/{id}")
    private ResponseEntity<University> get(@PathVariable Integer id){
        return ResponseEntity.ok(universityService.get(id));
    }

    @GetMapping()
    private ResponseEntity<Page<University>> getAll(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size
    ){
        return ResponseEntity.ok(universityService.getAll(size,page));
    }

    @PostMapping("/create")
    private ResponseEntity<University> create(@Valid @RequestBody UniversityCreateDTO dto){
        return ResponseEntity.ok(universityService.create(dto));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Integer id){
        return ResponseEntity.ok(universityService.delete(id));
    }

    @PostMapping("/update")
    private ResponseEntity<University> update(@Valid @RequestBody UniversityUpdateDTO dto){
        return ResponseEntity.ok(universityService.update(dto));
    }
}
