package com.ogoh.ogohtrack.model;

import jakarta.persistence.*;

/* Entity yang merepresentasikan data ogoh-ogoh pada database */
@Entity
@Table(name = "projects")
public class ProjectOgoh {

    /* Primary key untuk setiap data ogoh-ogoh */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* Informasi utama ogoh-ogoh */
    private String namaProyek;
    private String tema;
    private int tahun;
    private String deskripsi;

    /* Data tambahan ogoh-ogoh */
    private String gambar;
    private String namaStt;
    private String ketuaStt;

    /* Constructor kosong */
    public ProjectOgoh() {
    }

    /* Constructor untuk membuat objek ogoh-ogoh */
    public ProjectOgoh(String namaProyek, String tema, int tahun, String deskripsi) {
        this.namaProyek = namaProyek;
        this.tema = tema;
        this.tahun = tahun;
        this.deskripsi = deskripsi;
    }

    /* Getter ID */
    public Long getId() {
        return id;
    }

    /* Getter dan Setter nama ogoh-ogoh */
    public String getNamaProyek() {
        return namaProyek;
    }

    public void setNamaProyek(String namaProyek) {
        this.namaProyek = namaProyek;
    }

    /* Getter dan Setter tema ogoh-ogoh */
    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    /* Getter dan Setter tahun pembuatan */
    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    /* Getter dan Setter deskripsi ogoh-ogoh */
    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    /* Getter dan Setter gambar ogoh-ogoh */
    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    /* Getter dan Setter nama STT pembuat ogoh-ogoh */
    public String getNamaStt() {
        return namaStt;
    }

    public void setNamaStt(String namaStt) {
        this.namaStt = namaStt;
    }

    /* Getter dan Setter ketua STT */
    public String getKetuaStt() {
        return ketuaStt;
    }

    public void setKetuaStt(String ketuaStt) {
        this.ketuaStt = ketuaStt;
    }

    /* Slug digunakan untuk membuat URL ogoh-ogoh yang lebih rapi */
    private String slug;

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

}