package com.company.lmsforvacancy.service;

import com.company.lmsforvacancy.domain.Faculty;
import com.company.lmsforvacancy.domain.University;
import com.company.lmsforvacancy.dto.faculty.FacultyCreateDTO;
import com.company.lmsforvacancy.exceptions.ItemNotFoundException;
import com.company.lmsforvacancy.repository.FacultyRepository;
import com.company.lmsforvacancy.repository.UniversityRepository;
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
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "faculty")
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final UniversityService universityService;

    @Cacheable(key = "#id")
    public Faculty get(@NonNull Integer id) {
        return facultyRepository.findById(id)
                .orElseThrow(()->new ItemNotFoundException("Faculty not found with id : "+id));
    }

    public Faculty create(FacultyCreateDTO dto) {
        University university = universityService.get(dto.getUniversityId());
        return facultyRepository.save(
                Faculty.builder()
                        .name(dto.getName())
                        .university(university)
                        .build());
    }

    @CacheEvict(key = "#id")
    public boolean delete(@NonNull Integer id) {
        facultyRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Faculty not found with id : " + id));
        facultyRepository.delete(id);
        return true;
    }

    @CachePut(key = "#result.id")
    public Faculty update(Integer id,String name) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Faculty not found with id : " + id));
        faculty.setName(name);
        facultyRepository.save(faculty);
        return faculty;
    }

    @Cacheable
    public Page<Faculty> getAll(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        return facultyRepository.findAll(pageable);
    }
}
