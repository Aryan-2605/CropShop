package CropShop.Support.controllers;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import CropShop.Support.adminAuth.JWTService;
import CropShop.Support.repositories.adminRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import CropShop.Support.dto.UserPostDTO;
import CropShop.Support.model.admin;
import CropShop.Support.services.adminService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class adminAuthController {

    @Autowired
    adminService userService;
    @Autowired
    private AuthenticationManager authenticationManager;



    // Get All Users
    @GetMapping("/admins")
    public List<admin> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/create")
    public ResponseEntity<?> addUser(@RequestBody UserPostDTO newUserDTO) {

        String[] usernames = userService.getAllUsernames();

        for(int x = 0; x < usernames.length; x++){
            if (usernames[x].equals(newUserDTO.getUsername())) {
                return new ResponseEntity<>("Username Already Exists!", HttpStatus.BAD_REQUEST);
            }
        }


        if (newUserDTO.getUsername() == null || newUserDTO.getPassword() == null) {
            return new ResponseEntity<>("Username and password are required", HttpStatus.BAD_REQUEST);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        admin newUser = new admin(newUserDTO.getUsername(), encoder.encode(newUserDTO.getPassword()));
        userService.addUser(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateAdmin(@RequestBody admin loginAdmin, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginAdmin.getPassword(), loginAdmin.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        if (authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.SEE_OTHER).header(HttpHeaders.LOCATION, "localhost:3000/admin/dashboard").build();

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }




    // Get User by ID
    @GetMapping("/admin/{id}")
    public Optional<admin> getUserById(@PathVariable(value = "id") long Id) {
        return userService.findByID(Id);
    }


    //Delete a User by ID
    @DeleteMapping("/admins/{id}")
    public String deleteUser(@PathVariable(value = "id") long Id) {
        userService.deleteUser(Id);
        return "User Deleted";
    }

    //Get User by Email


}
