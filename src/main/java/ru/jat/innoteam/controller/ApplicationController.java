package ru.jat.innoteam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.jat.innoteam.model.Application;
import ru.jat.innoteam.repository.ApplicationRepository;

@RequiredArgsConstructor
@RestController("/api/application")
public class ApplicationController {
    private final ApplicationRepository applicationRepository;

    @GetMapping("/")
    public Page<Application> loadApplicationsPage(Pageable pageable) {
        return applicationRepository.findAll(pageable);
    }
}
