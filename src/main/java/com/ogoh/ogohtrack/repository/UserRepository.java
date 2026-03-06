package com.ogoh.ogohtrack.repository;

import com.ogoh.ogohtrack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/* Repository untuk mengakses data pengguna pada database */
public interface UserRepository extends JpaRepository<User, Long> {

    /* Method untuk mencari user berdasarkan username */
    User findByUsername(String username);

}