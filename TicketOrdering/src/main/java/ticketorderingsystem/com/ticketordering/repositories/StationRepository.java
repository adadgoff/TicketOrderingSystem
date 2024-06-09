package ticketorderingsystem.com.ticketordering.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ticketorderingsystem.com.ticketordering.models.StationModel;

@Repository
public interface StationRepository extends JpaRepository<StationModel, Long> {
}
