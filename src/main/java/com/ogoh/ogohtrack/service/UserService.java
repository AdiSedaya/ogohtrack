package com.ogoh.ogohtrack.service;

import com.ogoh.ogohtrack.model.User;
import com.ogoh.ogohtrack.repository.UserRepository;
import org.springframework.stereotype.Service;

/* Service untuk menangani logika bisnis terkait pengguna */
@Service
public class UserService {

    /* Repository untuk mengakses data user pada database */
    private final UserRepository userRepository;

    /* Constructor untuk menghubungkan UserRepository */
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /* Proses login dengan memvalidasi username dan password */
    public User login(String username, String password){

        User user = userRepository.findByUsername(username);

        if(user != null && user.getPassword().equals(password)){
            return user;
        }

        return null;
    }

    /* Mengambil data user berdasarkan username */
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    /* Menyimpan atau memperbarui data user */
    public void saveUser(User user){
        userRepository.save(user);
    }
}