package com.company.lmsforvacancy.controller;

import com.company.lmsforvacancy.domain.Journal;
import com.company.lmsforvacancy.dto.journal.JournalCreateDTO;
import com.company.lmsforvacancy.service.JournalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/journal")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class JournalController {
    private final JournalService journalService;

    @GetMapping("/{id}")
    private ResponseEntity<Journal> get(@PathVariable Integer id) {
        return ResponseEntity.ok(journalService.get(id));
    }

    @GetMapping()
    private ResponseEntity<Page<Journal>> getAll(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size
    ) {
        return ResponseEntity.ok(journalService.getAll(size, page));
    }

    @PostMapping("/create")
    private ResponseEntity<Journal> create(@Valid @RequestBody JournalCreateDTO dto) {
        return ResponseEntity.ok(journalService.create(dto));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(journalService.delete(id));
    }

    @PostMapping("/update")
    private ResponseEntity<Journal> update(
            @RequestParam("id") Integer id,
            @RequestParam("name") String name
    ) {
        return ResponseEntity.ok(journalService.update(name,id));
    }

    @PostMapping("/add-subject")
    private ResponseEntity<Journal> addSubject(
            @RequestParam("subject_id") Integer subjectId,
            @RequestParam("journal_id") Integer journalId
    ){
        return ResponseEntity.ok(journalService.addSubject(journalId, subjectId));
    }
}
