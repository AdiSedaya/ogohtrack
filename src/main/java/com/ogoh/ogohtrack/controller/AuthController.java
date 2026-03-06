package com.ogoh.ogohtrack.controller;

import com.ogoh.ogohtrack.model.User;
import com.ogoh.ogohtrack.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/* Controller untuk menangani proses autentikasi (login dan logout) */
@Controller
public class AuthController {

    /* Service untuk mengakses data user dari database */
    private final UserService userService;

    /* Constructor untuk menghubungkan UserService */
    public AuthController(UserService userService){
        this.userService = userService;
    }

    /* Menampilkan halaman login */
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    /* Memproses data login dari form */
@PostMapping("/login")
public String login(
        @RequestParam String username,
        @RequestParam String password,
        HttpSession session,
        Model model){

    /* Mengambil data user berdasarkan username */
    User user = userService.findByUsername(username);

    /* Validasi username dan password */
    if(user != null && user.getPassword().equals(password)){

    /* Menyimpan data user ke session setelah login berhasil */
    session.setAttribute("username", user.getUsername());
    session.setAttribute("role", user.getRole());
    session.setAttribute("nama", user.getNama());

        return "redirect:/dashboard";
    }

    /* login gagal tampilkan pesan error */
    model.addAttribute("error", "Username atau password salah");
    return "login";
}

    /* Proses logout */
    @GetMapping("/logout")
    public String logout(HttpSession session){

        session.invalidate();

        return "redirect:/login";
    }
}