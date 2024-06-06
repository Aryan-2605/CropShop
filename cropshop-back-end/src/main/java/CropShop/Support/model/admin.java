package CropShop.Support.model;

import lombok.*;

import jakarta.persistence.*;



@Entity
@Data
public class admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    public admin() {
        // Default constructor
    }

    public admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin [id=" + id + ", username=" + username + ", password=" + password + "]";
    }

    // Other methods, getters, and setters
}
