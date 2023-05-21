package com.company.lmsforvacancy.controller;

import com.company.lmsforvacancy.domain.Group;
import com.company.lmsforvacancy.dto.group.GroupCreateDTO;
import com.company.lmsforvacancy.dto.group.GroupUpdateDTO;
import com.company.lmsforvacancy.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @GetMapping("/{id}")
    private ResponseEntity<Group> get(@PathVariable Integer id) {
        return ResponseEntity.ok(groupService.get(id));
    }

    @GetMapping()
    private ResponseEntity<Page<Group>> getAll(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size
    ) {
        return ResponseEntity.ok(groupService.getAll(size, page));
    }

    @PostMapping("/create")
    private ResponseEntity<Group> create(@Valid @RequestBody GroupCreateDTO dto) {
        return ResponseEntity.ok(groupService.create(dto));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(groupService.delete(id));
    }

    @PostMapping("/update")
    private ResponseEntity<Group> update(@Valid @RequestBody GroupUpdateDTO dto) {
        return ResponseEntity.ok(groupService.update(dto));
    }

//    @PostMapping("/add-subject")
//    private ResponseEntity<Group> addSubject(
//            @RequestParam("subject_id") Integer subjectId,
//            @RequestParam("group_id") Integer groupId
//    ){
//        return ResponseEntity.ok(groupService.addSubject(subjectId, groupId));
//    }

    //additional


}
