package com.ogoh.ogohtrack.model;

import jakarta.persistence.*;

/* Entity yang merepresentasikan data pengguna pada sistem OgohTrack */
@Entity
@Table(name="users")
public class User {

    /* Primary key untuk setiap user */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* Data akun pengguna */
    private String username;
    private String password;
    private String role;

    /* Data profil pengguna */
    private String nama;
    private String hp;

    /* Constructor kosong */
    public User(){}

    /* Getter ID */
    public Long getId(){ return id; }

    /* Getter dan Setter nama pengguna */
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    /* Getter dan Setter nomor HP pengguna */
    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    /* Getter dan Setter username */
    public String getUsername(){ return username; }
    public void setUsername(String username){ this.username = username; }

    /* Getter dan Setter password */
    public String getPassword(){ return password; }
    public void setPassword(String password){ this.password = password; }

    /* Getter dan Setter role (admin / user) */
    public String getRole(){ return role; }
    public void setRole(String role){ this.role = role; }

}