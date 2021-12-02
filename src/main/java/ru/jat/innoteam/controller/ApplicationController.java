package ru.jat.innoteam.controller;

import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.jat.innoteam.model.Application;
import ru.jat.innoteam.repository.ApplicationRepository;

import javax.validation.constraints.NotNull;

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

    @GetMapping("/search")
    public Page<Application> fuzzySearch(@RequestParam String q, Pageable pageable) {
        return applicationRepository.find(q, pageable);
    }
}
