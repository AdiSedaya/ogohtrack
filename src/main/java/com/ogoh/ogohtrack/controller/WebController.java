package com.ogoh.ogohtrack.controller;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import com.ogoh.ogohtrack.model.User;
import com.ogoh.ogohtrack.model.ProjectOgoh;
import com.ogoh.ogohtrack.service.ProjectService;
import com.ogoh.ogohtrack.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.Path;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.itextpdf.layout.Document;

import java.util.List;
import java.util.stream.Collectors;
import java.time.Year;

/* Controller utama untuk menangani halaman web pada sistem OgohTrack */
@Controller
public class WebController {

    /* Service untuk mengelola data ogoh-ogoh */
    private final ProjectService projectService;

    /* Service untuk mengelola data user */
    private final UserService userService;

public WebController(ProjectService projectService, UserService userService) {
    this.projectService = projectService;
    this.userService = userService;
}

/* Menampilkan halaman dashboard */
@GetMapping("/dashboard")
public String dashboard(Model model, HttpSession session){

    List<ProjectOgoh> allProjects = projectService.getAllProjects();

    int currentYear = Year.now().getValue();

    /* Filter ogoh-ogoh tahun sekarang (trending) */
    List<ProjectOgoh> trending = allProjects.stream()
            .filter(p -> p.getTahun() == currentYear)
            .collect(Collectors.toList());

    /* Filter ogoh-ogoh tahun sebelumnya */
    List<ProjectOgoh> lastYear = allProjects.stream()
            .filter(p -> p.getTahun() < currentYear)
            .collect(Collectors.toList());

    model.addAttribute("trendingProjects", trending);
    model.addAttribute("lastYearProjects", lastYear);

    /* Mengambil role user dari session */
    String role = (String) session.getAttribute("role");
    model.addAttribute("role", role);

    /* Mengambil data user yang sedang login */
    String username = (String) session.getAttribute("username");
    User user = userService.findByUsername(username);

    model.addAttribute("user", user);

    return "dashboard";
}

/* Menampilkan halaman tambah ogoh-ogoh */
@GetMapping("/add")
public String addPage(){
    return "add";
}

/* Menghapus data ogoh-ogoh */
@GetMapping("/delete/{id}")
public String deleteProject(@PathVariable Long id){

projectService.deleteProject(id);

return "redirect:/dashboard";
}

/* Menampilkan halaman edit ogoh-ogoh */
@GetMapping("/edit/{id}")
public String editProject(@PathVariable Long id, Model model){

ProjectOgoh project = projectService.getProjectById(id);

model.addAttribute("project", project);

return "edit";
}

/* Menampilkan detail ogoh-ogoh */
@GetMapping("/project/{id}")
public String projectDetail(@PathVariable Long id, Model model){

ProjectOgoh project = projectService.getProjectById(id);

model.addAttribute("project", project);

return "detail";
}

/* Mengunduh detail ogoh-ogoh dalam bentuk PDF */
@GetMapping("/project/pdf/{id}")
public void downloadPdf(@PathVariable Long id, HttpServletResponse response) throws Exception {

ProjectOgoh project = projectService.getProjectById(id);

response.setContentType("application/pdf");
response.setHeader("Content-Disposition", "attachment; filename=ogoh.pdf");

PdfWriter writer = new PdfWriter(response.getOutputStream());
PdfDocument pdf = new PdfDocument(writer);
Document document = new Document(pdf);

document.add(new Paragraph("Detail Ogoh-Ogoh"));
document.add(new Paragraph("Nama: " + project.getNamaProyek()));
document.add(new Paragraph("Tema: " + project.getTema()));
document.add(new Paragraph("Tahun: " + project.getTahun()));
document.add(new Paragraph("STT: " + project.getNamaStt()));
document.add(new Paragraph("Ketua STT: " + project.getKetuaStt()));
document.add(new Paragraph("Deskripsi: " + project.getDeskripsi()));

document.close();

}

/* Menampilkan detail ogoh-ogoh berdasarkan slug URL */
@GetMapping("/ogoh-ogoh/{slug}")
public String projectDetailSlug(@PathVariable String slug, Model model){

    ProjectOgoh project = projectService.getProjectBySlug(slug);

    model.addAttribute("project", project);

    return "detail";
}

/* Fitur pencarian ogoh-ogoh */
@GetMapping("/search")
public String searchOgoh(
        @RequestParam String keyword,
        Model model,
        HttpSession session){

    List<ProjectOgoh> results = projectService.searchProjects(keyword);

    model.addAttribute("trendingProjects", results);
    model.addAttribute("lastYearProjects", List.of());

    String role = (String) session.getAttribute("role");
    model.addAttribute("role", role);

    String username = (String) session.getAttribute("username");
    User user = userService.findByUsername(username);
    model.addAttribute("user", user);

    return "dashboard";
}

/* Menampilkan halaman profil user */
@GetMapping("/profile")
public String profilePage(HttpSession session, Model model){

    String username = (String) session.getAttribute("username");

    if(username == null){
        return "redirect:/login";
    }

    User user = userService.findByUsername(username);

    model.addAttribute("user", user);

    return "profile";
}

/* Memperbarui data profil user */
@PostMapping("/profile/update")
public String updateProfile(
@RequestParam String nama,
@RequestParam String hp,
HttpSession session
){

    String username = (String) session.getAttribute("username");

    User user = userService.findByUsername(username);

    user.setNama(nama);
    user.setHp(hp);

    userService.saveUser(user);

    return "redirect:/dashboard";
}

/* Menyimpan data ogoh-ogoh baru */
@PostMapping("/save")
public String saveProject(
@RequestParam String namaProyek,
@RequestParam String tema,
@RequestParam int tahun,
@RequestParam String deskripsi,
@RequestParam String namaStt,
@RequestParam String ketuaStt,
@RequestParam MultipartFile gambar
) throws Exception {

    String fileName = gambar.getOriginalFilename();

    Path uploadPath = Paths.get(System.getProperty("user.dir"), "uploads"); 

    Files.copy(
        gambar.getInputStream(),
        uploadPath.resolve(fileName),
        StandardCopyOption.REPLACE_EXISTING
    );

    ProjectOgoh project = new ProjectOgoh();

    project.setNamaProyek(namaProyek);
    project.setTema(tema);
    project.setTahun(tahun);
    project.setDeskripsi(deskripsi);
    project.setNamaStt(namaStt);
    project.setKetuaStt(ketuaStt);
    project.setGambar(fileName);

    project.setSlug(createSlug(namaProyek));

    projectService.saveProject(project);

    return "redirect:/dashboard";
}

/* Memperbarui data ogoh-ogoh */
@PostMapping("/update")
public String updateProject(
@RequestParam Long id,
@RequestParam String namaProyek,
@RequestParam String tema,
@RequestParam int tahun,
@RequestParam String deskripsi,
@RequestParam String namaStt,
@RequestParam String ketuaStt,
@RequestParam MultipartFile gambar
) throws Exception{

ProjectOgoh project = projectService.getProjectById(id);

project.setNamaProyek(namaProyek);
project.setTema(tema);
project.setTahun(tahun);
project.setDeskripsi(deskripsi);
project.setNamaStt(namaStt);
project.setKetuaStt(ketuaStt);
project.setSlug(createSlug(namaProyek));

if(!gambar.isEmpty()){

String fileName = gambar.getOriginalFilename();

Path uploadPath = Paths.get(System.getProperty("user.dir"), "uploads");

Files.copy(
gambar.getInputStream(),
uploadPath.resolve(fileName),
StandardCopyOption.REPLACE_EXISTING
);

project.setGambar(fileName);

}

projectService.saveProject(project);

return "redirect:/dashboard";
}

/* Membuat slug URL dari nama ogoh-ogoh */
private String createSlug(String text) {
    return text.toLowerCase()
            .replace(" ", "-")
            .replaceAll("[^a-z0-9-]", "");
}

}