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
import ru.jat.innoteam.model.Issue;
import ru.jat.innoteam.repository.IssueRepository;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/issue")
public class IssueController {

    private final IssueRepository issueRepository;

    @GetMapping
    public Page<Issue> getIssues(@NotNull @ParameterObject Pageable pageable) {
        return issueRepository.findAll(pageable);
    }

    @GetMapping("/{uuid}")
    public Optional<Issue> getIssueByUuid(@PathVariable("uuid") String uuid) {
        return issueRepository.findById(UUID.fromString(uuid));
    }

    @PostMapping
    public Issue saveIssue(@RequestBody Issue issue) {
        return issueRepository.save(issue);
    }

    @DeleteMapping("/{uuid}")
    public void removeIssueByUuid(@PathVariable("uuid") String uuid) {
        issueRepository.deleteById(UUID.fromString(uuid));
    }

    @Operation(summary = "Нечеткий поиск")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Выполнено успешно"),
    })
    @GetMapping("/search")
    public Page<Application> fuzzySearch(
            @Parameter(description = "Текст поиска", example = "текст")
            @RequestParam String q, Pageable pageable) {
        return issueRepository.find(q, pageable);
    }

    @Operation(summary = "Полнотекстовый поиск")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Выполнено успешно"),
    })
    @GetMapping("/search/fulltext")
    public Page<Application> fullTextSearch(
            @Parameter(description = "Текст поиска", example = "текст")
            @RequestParam String q, Pageable pageable) {
        return issueRepository.multiFieldFullTextSearch(q, pageable);
    }

}
