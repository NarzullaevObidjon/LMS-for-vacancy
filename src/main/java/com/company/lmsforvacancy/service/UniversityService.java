package com.company.lmsforvacancy.service;

import com.company.lmsforvacancy.domain.University;
import com.company.lmsforvacancy.dto.university.UniversityCreateDTO;
import com.company.lmsforvacancy.dto.university.UniversityUpdateDTO;
import com.company.lmsforvacancy.exceptions.ItemNotFoundException;
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

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "university")
public class UniversityService {
    private final UniversityRepository universityRepository;

    @Cacheable(key = "#id")
    public University get(@NonNull Integer id) {
        return universityRepository.findById(id)
                .orElseThrow(()->new ItemNotFoundException("University not found with id : "+id));
    }

    public University create(UniversityCreateDTO dto) {
        return universityRepository.save(
                University.builder()
                        .name(dto.getName())
                        .address(dto.getAddress())
                        .openYear(dto.getOpenYear())
                        .build());
    }

    @CacheEvict(key = "#id")
    public boolean delete(@NonNull Integer id) {
        universityRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("University not found with id : " + id));
        universityRepository.delete(id);
        return true;
    }

    @CachePut(key = "#result.id")
    public University update(UniversityUpdateDTO dto) {
        University university = universityRepository.findById(dto.getId())
                .orElseThrow(() -> new ItemNotFoundException("University not found with id : " + dto.getId()));
            university.setAddress(dto.getAddress());
            university.setName(dto.getName());
            university.setOpenYear(dto.getOpenYear());
        universityRepository.save(university);
        return university;
    }

    @Cacheable
    public Page<University> getAll(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        return universityRepository.findAll(pageable);
    }
}
