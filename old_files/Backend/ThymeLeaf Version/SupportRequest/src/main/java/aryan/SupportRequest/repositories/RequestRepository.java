package aryan.SupportRequest.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import aryan.SupportRequest.model.request;
@Repository
public interface RequestRepository extends JpaRepository<request, Long> {
	
	

}
