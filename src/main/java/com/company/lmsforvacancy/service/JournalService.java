package com.company.lmsforvacancy.service;

import com.company.lmsforvacancy.domain.Group;
import com.company.lmsforvacancy.domain.Journal;
import com.company.lmsforvacancy.domain.Student;
import com.company.lmsforvacancy.domain.Subject;
import com.company.lmsforvacancy.dto.journal.JournalCreateDTO;
import com.company.lmsforvacancy.exceptions.ItemNotFoundException;
import com.company.lmsforvacancy.repository.JournalRepository;
import com.company.lmsforvacancy.repository.StudentRepository;
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

import java.util.List;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "journal")
public class JournalService {
    private final JournalRepository journalRepository;
    private final GroupService groupService;
    private final SubjectService subjectService;
    private final StudentRepository studentRepository;

    @Cacheable(key = "#id")
    public Journal get(@NonNull Integer id) {
        return journalRepository.findById(id)
                .orElseThrow(()->new ItemNotFoundException("Journal not found with id : "+id));
    }

    public Journal create(@NonNull JournalCreateDTO dto) {
        Group group = groupService.get(dto.getGroupId());

        return journalRepository.save(
                Journal.builder()
                        .name(dto.getName())
                        .group(group)
                        .build());
    }

    @CacheEvict(key = "#id")
    public boolean delete(@NonNull Integer id) {
        get(id);
        journalRepository.delete(id);
        return true;
    }

    @CachePut(key = "#result.id")
    public Journal update(String name, Integer id) {
        Journal journal = get(id);
        journal.setName(name);
        journalRepository.save(journal);
        return journal;
    }

    public Page<Journal> getAll(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        return journalRepository.findAll(pageable);
    }

    @CachePut(key = "#journalId")
    public Journal addSubject(Integer journalId, Integer subjectId) {
        Subject subject = subjectService.get(subjectId);
        Journal journal = get(journalId);
        journal.getSubjects().add(subject);
        journalRepository.save(journal);
        return journal;
    }

    public List<Subject> getSubjects(Integer id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Student not found with id : " + id));

        return journalRepository.getSubjectsById(student.getGroup().getId());

    }
}
