package CropShop.Support.repositories;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import CropShop.Support.model.request;

@Repository
public interface requestRepository extends JpaRepository<request, Long> {



}
