package ticketorderingsystem.com.ticketordering.services.implementations;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ticketorderingsystem.com.ticketordering.models.OrderModel;
import ticketorderingsystem.com.ticketordering.models.StationModel;
import ticketorderingsystem.com.ticketordering.models.enums.Status;
import ticketorderingsystem.com.ticketordering.repositories.OrderRepository;
import ticketorderingsystem.com.ticketordering.repositories.StationRepository;
import ticketorderingsystem.com.ticketordering.services.interfaces.IOrderService;

import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final StationRepository stationRepository;

    public OrderService(OrderRepository orderRepository, StationRepository stationRepository) {
        this.orderRepository = orderRepository;
        this.stationRepository = stationRepository;
    }

    @Override
    @Transactional
    public OrderModel createOrder(long fromStationId, long toStationId, long userId) {
        Optional<StationModel> fromStation = stationRepository.findById(fromStationId);
        Optional<StationModel> toStation = stationRepository.findById(toStationId);

        if (fromStation.isEmpty() || toStation.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Station not found.");
        }

        OrderModel orderModel = OrderModel.builder()
                .userId(userId)
                .fromStation(fromStation.get())
                .toStation(toStation.get())
                .status(Status.CHECK)
                .build();

        orderRepository.save(orderModel);
        return orderModel;
    }

    @Override
    public OrderModel getOrder(long orderId) {
        Optional<OrderModel> orderModelOptional = orderRepository.findById(orderId);

        if (orderModelOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found.");
        }

        return orderModelOptional.get();
    }
}
