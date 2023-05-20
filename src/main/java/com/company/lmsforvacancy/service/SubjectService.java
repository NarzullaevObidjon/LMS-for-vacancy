package com.company.lmsforvacancy.service;

import com.company.lmsforvacancy.domain.Faculty;
import com.company.lmsforvacancy.domain.Subject;
import com.company.lmsforvacancy.dto.group.GroupUpdateDTO;
import com.company.lmsforvacancy.exceptions.ItemNotFoundException;
import com.company.lmsforvacancy.repository.GroupRepository;
import com.company.lmsforvacancy.repository.SubjectRepository;
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
@CacheConfig(cacheNames = "subject")
public class SubjectService {
    private final SubjectRepository subjectRepository;

    @Cacheable(key = "#id")
    public Subject get(@NonNull Integer id) {
        return subjectRepository.findById(id)
                .orElseThrow(()->new ItemNotFoundException("Subject not found with id : "+id));
    }

    public Subject create(@NonNull String name) {
        return subjectRepository.save(
                Subject.builder()
                        .name(name)
                        .build());
    }

    @CacheEvict(key = "#id")
    public boolean delete(@NonNull Integer id) {
        get(id);
        subjectRepository.delete(id);
        return true;
    }

    @CachePut(key = "#result.id")
    public Subject update(String name, Integer id) {
        Subject subject = get(id);
                subject.setName(name);
        subjectRepository.save(subject);
        return subject;
    }

    public Page<Subject> getAll(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        return subjectRepository.findAll(pageable);
    }
}
