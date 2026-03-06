package com.ogoh.ogohtrack.controller;

import com.ogoh.ogohtrack.model.ProjectOgoh;
import com.ogoh.ogohtrack.repository.ProjectRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* Controller REST untuk mengelola data ogoh-ogoh melalui API */
@RestController
@RequestMapping("/projects")
public class ProjectController {

    /* Repository untuk mengakses data ogoh-ogoh dari database */
    private final ProjectRepository projectRepository;

    /* Constructor untuk menghubungkan ProjectRepository */
    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    /* Endpoint untuk mengambil seluruh data ogoh-ogoh */
    @GetMapping
    public List<ProjectOgoh> getAllProjects() {
        return projectRepository.findAll();
    }
    
    /* Endpoint untuk menambahkan data ogoh-ogoh baru */
    @PostMapping
    public ProjectOgoh createProject(@RequestBody ProjectOgoh projectOgoh) {
        return projectRepository.save(projectOgoh);
    }
}