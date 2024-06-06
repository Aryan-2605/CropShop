package CropShop.Support.repositories;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import CropShop.Support.model.archivedRequest;

@Repository
public interface archivedRequestRepository extends JpaRepository<archivedRequest, Long> {



}
