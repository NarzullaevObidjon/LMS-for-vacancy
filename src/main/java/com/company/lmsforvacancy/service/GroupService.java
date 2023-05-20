package com.company.lmsforvacancy.service;

import com.company.lmsforvacancy.domain.Faculty;
import com.company.lmsforvacancy.domain.Group;
import com.company.lmsforvacancy.domain.Subject;
import com.company.lmsforvacancy.dto.group.GroupCreateDTO;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "group")
public class GroupService {
    private final GroupRepository groupRepository;
    private final FacultyService facultyService;
    private final SubjectService subjectService;

    @Cacheable(key = "#id")
    public Group get(@NonNull Integer id) {
        return groupRepository.findById(id)
                .orElseThrow(()->new ItemNotFoundException("Group not found with id : "+id));
    }

    public Group create(GroupCreateDTO dto) {
        Faculty faculty = facultyService.get(dto.getFacultyId());
        return groupRepository.save(
                Group.builder()
                        .name(dto.getName())
                        .faculty(faculty)
                        .year(dto.getYear())
                        .build());
    }

    @CacheEvict(key = "#id")
    public boolean delete(@NonNull Integer id) {
        get(id);
        groupRepository.delete(id);
        return true;
    }

    @CachePut(key = "#result.id")
    public Group update(GroupUpdateDTO dto) {
        Group group = get(dto.getId());
        group.setName(dto.getName());
        group.setYear(dto.getYear());
        groupRepository.save(group);
        return group;
    }

    public Page<Group> getAll(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        return groupRepository.findAll(pageable);
    }

    @CachePut(key = "#groupId")
    public Group addSubject(Integer subjectId, Integer groupId) {
        Group group = get(groupId);
        Subject subject = subjectService.get(subjectId);
        group.getSubjects().add(subject);
        groupRepository.save(group);
        return group;
    }
}
