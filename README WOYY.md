# OgohTrack

OgohTrack adalah aplikasi web untuk tracking dan menampilkan data ogoh-ogoh dari berbagai STT (Sekaa Teruna-Teruni) di Bali. Dibangun menggunakan **Java Spring Boot**, **MySQL**, dan **Thymeleaf**.

---

##  Persyaratan

Pastikan sudah menginstall:

- [Java JDK 21](https://www.oracle.com/java/technologies/downloads/)
- [Maven](https://maven.apache.org/download.cgi) (atau gunakan Maven Wrapper yang sudah tersedia)
- [MySQL](https://dev.mysql.com/downloads/installer/) versi 8 ke atas
- [Git](https://git-scm.com/downloads)

---

##  Cara Menjalankan di Lokal

### 1. Clone Repository

```bash
git clone https://github.com/AdiSedaya/ogohtrack.git
cd ogohtrack
```

### 2. Buat Database MySQL

Buka MySQL Workbench atau terminal MySQL, lalu jalankan:

```sql
CREATE DATABASE ogohtrack_db;
```

### 3. Konfigurasi `application.properties`

Buka file `src/main/resources/application.properties` dan sesuaikan dengan konfigurasi MySQL lokal kamu:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ogohtrack_db
spring.datasource.username=root
spring.datasource.password=password_kamu
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
server.port=8080
```

### 4. Jalankan Aplikasi

```bash
./mvnw spring-boot:run
```

Atau di Windows:

```bash
mvnw.cmd spring-boot:run
```

### 5. Buka di Browser

```
http://localhost:8080/login
```

### 6. Insert User ke Database

Karena tidak ada fitur register, tambahkan user manual melalui MySQL Workbench atau terminal:

```sql
USE ogohtrack_db;

INSERT INTO users (username, password, nama, role, hp)
VALUES 
('admin', 'admin123', 'Administrator', 'ADMIN', '081234567890'),
('user', 'user123', 'User Biasa', 'USER', '081234567891');
```

### 7. Login

| Role | Username | Password |
|------|----------|----------|
| Admin | `admin` | `admin123` |
| User | `user` | `user123` |

---

##  Akses Online (Railway)

Aplikasi sudah di-deploy dan bisa diakses di:

```
https://ogohtrack-production-285f.up.railway.app/login
```

---

##  Struktur Project

```
ogohtrack/
├── src/main/java/com/ogoh/ogohtrack/
│   ├── OgohtrackApplication.java      # Entry point aplikasi
│   ├── WebMvcConfig.java              # Konfigurasi resource handler
│   ├── controller/
│   │   ├── AuthController.java        # Handle login & logout
│   │   ├── WebController.java         # Handle halaman web utama
│   │   └── ProjectController.java     # REST API ogoh-ogoh
│   ├── model/
│   │   ├── ProjectOgoh.java           # Entity tabel projects
│   │   └── User.java                  # Entity tabel users
│   ├── repository/
│   │   ├── ProjectRepository.java     # Akses data ogoh-ogoh
│   │   └── UserRepository.java        # Akses data user
│   └── service/
│       ├── ProjectService.java        # Logika bisnis ogoh-ogoh
│       └── UserService.java           # Logika bisnis user
├── src/main/resources/
│   ├── templates/                     # File HTML Thymeleaf
│   │   ├── login.html
│   │   ├── dashboard.html
│   │   ├── add.html
│   │   ├── edit.html
│   │   ├── detail.html
│   │   └── profile.html
│   ├── static/                        # File statis (gambar, css)
│   │   └── Background.jpg
│   └── application.properties        # Konfigurasi aplikasi
└── pom.xml                            # Dependency Maven
```

---

##  Fitur Aplikasi

| Fitur | Admin | User |
|-------|-------|------|
| Login | ✅ | ✅ |
| Lihat Dashboard | ✅ | ✅ |
| Cari Ogoh-Ogoh | ✅ | ✅ |
| Lihat Detail | ✅ | ✅ |
| Download PDF | ✅ | ✅ |
| Tambah Data | ✅ | ❌ |
| Edit Data | ✅ | ❌ |
| Hapus Data | ✅ | ❌ |
| Edit Profile | ✅ | ✅ |

---

##  Tech Stack

- **Backend** : Java 21, Spring Boot 4, Spring Data JPA
- **Frontend** : HTML, CSS, Thymeleaf
- **Database** : MySQL
- **Library** : iText PDF
- **Deploy** : Railway

---

##  Developer

Dibuat oleh **Adi Sedaya**
