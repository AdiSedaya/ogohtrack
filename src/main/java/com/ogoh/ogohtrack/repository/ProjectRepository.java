package com.ogoh.ogohtrack.repository;

import com.ogoh.ogohtrack.model.ProjectOgoh;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/* Repository untuk mengakses data ogoh-ogoh dari database */
public interface ProjectRepository extends JpaRepository<ProjectOgoh, Long> {

    /* Mencari data ogoh-ogoh berdasarkan slug (URL friendly identifier) */
    ProjectOgoh findBySlug(String slug);

    /* 
       Method pencarian ogoh-ogoh berdasarkan:
       - Nama Ogoh-Ogoh
       - Nama STT
       - Tema

       Pencarian menggunakan keyword dan tidak sensitif terhadap huruf besar/kecil
    */
    List<ProjectOgoh> findByNamaProyekContainingIgnoreCaseOrNamaSttContainingIgnoreCaseOrTemaContainingIgnoreCase(
        String namaProyek,
        String namaStt,
        String tema
    );
    
}