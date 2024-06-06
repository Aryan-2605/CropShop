package CropShop.Support.services;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import CropShop.Support.repositories.adminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CropShop.Support.model.admin;
@Service
public class adminService {
    @Autowired
    adminRepository userRepository;



    public List<admin> getUsers() {
        return (List<admin>) userRepository.findAll();
    }

    public void addUser(admin newUser) {
        userRepository.save(newUser);
    }

    public Optional<admin> findByID(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(Long id) {
        Optional<admin> adminOptional = userRepository.findById(id);
        adminOptional.ifPresent(admin -> userRepository.delete(admin));
    }

    public admin findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public String[] getAllUsernames() {
        List<String> usernames = userRepository.findAll().stream()
                .map(admin::getUsername)
                .collect(Collectors.toList());
        return usernames.toArray(new String[0]);
    }

}
