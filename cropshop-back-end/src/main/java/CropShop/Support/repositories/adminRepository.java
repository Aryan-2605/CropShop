package CropShop.Support.repositories;

import CropShop.Support.model.admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface adminRepository extends JpaRepository<admin, Long> {
    admin findByUsername(String username);



}
