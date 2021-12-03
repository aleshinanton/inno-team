package ru.jat.innoteam.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.jat.innoteam.model.Application;
import ru.jat.innoteam.repository.ApplicationRepository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * Контроллер заявок
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/application")
public class ApplicationController {
    private final ApplicationRepository applicationRepository;

    @Operation(summary = "Получение всех заявок")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Выполнено успешно"),
    })
    @GetMapping
    public Page<Application> loadApplicationsPage(@NotNull @ParameterObject Pageable pageable) {
        return applicationRepository.findAll(pageable);
    }

    @Operation(summary = "Получение заявки по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Выполнено успешно"),
    })
    @GetMapping("/{id}")
    public Optional<Application> getApplicationByUuid(
            @Parameter(description = "Идентификатор заявки", example = "1")
            @PathVariable("id") Long id) {
        return applicationRepository.findById(id);
    }

    @Operation(summary = "Нечеткий поиск")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Выполнено успешно"),
    })
    @GetMapping("/search")
    public Page<Application> fuzzySearch(
            @Parameter(description = "Текст поиска", example = "текст")
            @RequestParam String q, Pageable pageable) {
        return applicationRepository.find(q, pageable);
    }

    @Operation(summary = "Полнотекстовый поиск")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Выполнено успешно"),
    })
    @GetMapping("/search/fulltext")
    public Page<Application> fullTextSearch(
            @Parameter(description = "Текст поиска", example = "текст")
            @RequestParam String q, Pageable pageable) {
        return applicationRepository.multiFieldFullTextSearch(q, pageable);
    }
}
