package ru.jat.innoteam.controller;

import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.jat.innoteam.model.Application;
import ru.jat.innoteam.model.Issue;
import ru.jat.innoteam.repository.ApplicationRepository;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/application")
public class ApplicationController {
    private final ApplicationRepository applicationRepository;

    @GetMapping
    public Page<Application> loadApplicationsPage(@NotNull @ParameterObject Pageable pageable) {
        return applicationRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Optional<Application> getApplicationByUuid(@PathVariable("id") Long id) {
        return applicationRepository.findById(id);
    }

    @GetMapping("/search")
    public Page<Application> fuzzySearch(@RequestParam String q, Pageable pageable) {
        return applicationRepository.find(q, pageable);
    }

    @GetMapping("/search/fulltext")
    public Page<Application> fullTextSearch(@RequestParam String q, Pageable pageable) {
        return applicationRepository.multiFieldFullTextSearch(q, pageable);
    }
}
