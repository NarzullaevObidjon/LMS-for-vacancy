package com.company.lmsforvacancy.service;

import com.company.lmsforvacancy.domain.Faculty;
import com.company.lmsforvacancy.domain.University;
import com.company.lmsforvacancy.dto.faculty.FacultyCreateDTO;
import com.company.lmsforvacancy.dto.faculty.FacultyGroupsDetail;
import com.company.lmsforvacancy.dto.group.GroupsStudents;
import com.company.lmsforvacancy.exceptions.ItemNotFoundException;
import com.company.lmsforvacancy.repository.FacultyRepository;
import com.company.lmsforvacancy.repository.GroupRepository;
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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "faculty")
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final UniversityService universityService;
    private final GroupRepository groupRepository;

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
        get(id);
        facultyRepository.delete(id);
        return true;
    }

    @CachePut(key = "#result.id")
    public Faculty update(Integer id,String name) {
        Faculty faculty = get(id);
        faculty.setName(name);
        facultyRepository.save(faculty);
        return faculty;
    }


    public Page<Faculty> getAll(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        return facultyRepository.findAll(pageable);
    }

    public FacultyGroupsDetail getGroupsDetail(Integer id) {
        Faculty faculty = get(id);
        List<Object[]> result = groupRepository.findIdsByFaculty(id);
        List<GroupsStudents> groupsStudents= new ArrayList<>();
        result.forEach(a->groupsStudents.add(new GroupsStudents((Integer) a[0], (Integer) a[1])));
        return FacultyGroupsDetail.builder()
                .id(faculty.getId())
                .groupsCount(groupsStudents.size())
                .name(faculty.getName())
                .groups(groupsStudents)
                .build();
    }
}
