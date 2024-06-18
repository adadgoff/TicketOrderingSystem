package ticketorderingsystem.com.ticketordering.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ticketorderingsystem.com.ticketordering.models.OrderModel;
import ticketorderingsystem.com.ticketordering.models.enums.Status;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {
    List<OrderModel> findAllByStatus(Status status);
}
