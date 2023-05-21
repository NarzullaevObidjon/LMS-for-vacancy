package com.company.lmsforvacancy.controller;

import com.company.lmsforvacancy.domain.Mark;
import com.company.lmsforvacancy.dto.mark.MarkCreateDTO;
import com.company.lmsforvacancy.service.MarkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mark")
@RequiredArgsConstructor
public class MarkController {
    private final MarkService markService;

    @GetMapping("/{id}")
    private ResponseEntity<Mark> get(@PathVariable Integer id) {
        return ResponseEntity.ok(markService.get(id));
    }

    @GetMapping()
    private ResponseEntity<Page<Mark>> getAll(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size
    ) {
        return ResponseEntity.ok(markService.getAll(size, page));
    }

    @PostMapping("/create")
    private ResponseEntity<Mark> create(@Valid @RequestBody MarkCreateDTO dto) {
        return ResponseEntity.ok(markService.create(dto));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(markService.delete(id));
    }

    @PostMapping("/update")
    private ResponseEntity<Mark> update(
            @RequestParam("id") Integer id,
            @RequestParam("mark") Byte mark
    ) {
        return ResponseEntity.ok(markService.update(id, mark));
    }

}
