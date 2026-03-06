package com.ogoh.ogohtrack.service;

import com.ogoh.ogohtrack.model.ProjectOgoh;
import com.ogoh.ogohtrack.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/* Service untuk menangani logika bisnis terkait data ogoh-ogoh */
@Service
public class ProjectService {

    /* Repository yang digunakan untuk mengakses database */
    private final ProjectRepository projectRepository;

    /* Constructor untuk menghubungkan ProjectRepository */
    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    /* Mengambil seluruh data ogoh-ogoh */
    public List<ProjectOgoh> getAllProjects(){
        return projectRepository.findAll();
    }

    /* Menyimpan data ogoh-ogoh baru atau memperbarui data */
    public void saveProject(ProjectOgoh project){
        projectRepository.save(project);
    }

    /* Menghapus data ogoh-ogoh berdasarkan ID */
    public void deleteProject(Long id){
        projectRepository.deleteById(id);
    }

    /* Mengambil data ogoh-ogoh berdasarkan ID */
    public ProjectOgoh getProjectById(Long id){
        return projectRepository.findById(id).orElse(null);
    }

    /* Mengambil data ogoh-ogoh berdasarkan slug */
    public ProjectOgoh getProjectBySlug(String slug){
        return projectRepository.findBySlug(slug);
    }

    /* Mencari ogoh-ogoh berdasarkan keyword (nama proyek, STT, atau tema) */
    public List<ProjectOgoh> searchProjects(String keyword){
        return projectRepository
            .findByNamaProyekContainingIgnoreCaseOrNamaSttContainingIgnoreCaseOrTemaContainingIgnoreCase(
                keyword, keyword, keyword
            );
    }
}