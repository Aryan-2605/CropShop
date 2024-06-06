package CropShop.Support.model;

import lombok.*;

import jakarta.persistence.*;
@Entity
@Data
@Getter
@Setter
public class request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 25)
    private String firstName;

    @Column(length = 25)
    private String lastName;

    @Column(length = 50)
    private String email;

    @Column(length = 50)
    private String title;

    @Column(length = 4000)
    private String query;
}
