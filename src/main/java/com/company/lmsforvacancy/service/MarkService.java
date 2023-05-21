package com.company.lmsforvacancy.service;

import com.company.lmsforvacancy.domain.*;
import com.company.lmsforvacancy.dto.group.GroupCreateDTO;
import com.company.lmsforvacancy.dto.group.GroupUpdateDTO;
import com.company.lmsforvacancy.dto.mark.MarkCreateDTO;
import com.company.lmsforvacancy.exceptions.ItemNotFoundException;
import com.company.lmsforvacancy.repository.GroupRepository;
import com.company.lmsforvacancy.repository.MarkRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "mark")
public class MarkService {
    private final MarkRepository markRepository;
    private final JournalService journalService;
    private final StudentService studentService;
    private final SubjectService subjectService;

    @Cacheable(key = "#id")
    public Mark get(@NonNull Integer id) {
        return markRepository.findById(id)
                .orElseThrow(()->new ItemNotFoundException("Mark not found with id : "+id));
    }

    public Mark create(MarkCreateDTO dto) {
        Journal journal = journalService.get(dto.getJournalId());
        Student student = studentService.getStudent(dto.getStudentId());
        Subject subject = subjectService.get(dto.getSubjectId());
        return markRepository.save(
                Mark.builder()
                        .mark(dto.getMark())
                        .journal(journal)
                        .subject(subject)
                        .student(student)
                        .build());
    }

    @CacheEvict(key = "#id")
    public boolean delete(@NonNull Integer id) {
        get(id);
        markRepository.delete(id);
        return true;
    }

    @CachePut(key = "#result.id")
    public Mark update(Integer id, byte mark) {
        Mark markk = get(id);
        markk.setMark(mark);
        markRepository.save(markk);
        return markk;
    }

    public Page<Mark> getAll(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        return markRepository.findAll(pageable);
    }
}
