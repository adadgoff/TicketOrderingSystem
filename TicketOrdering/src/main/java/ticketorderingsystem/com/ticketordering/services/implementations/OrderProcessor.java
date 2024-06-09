package ticketorderingsystem.com.ticketordering.services.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ticketorderingsystem.com.ticketordering.models.OrderModel;
import ticketorderingsystem.com.ticketordering.models.enums.Status;
import ticketorderingsystem.com.ticketordering.repositories.OrderRepository;
import ticketorderingsystem.com.ticketordering.services.interfaces.IOrderProcessor;

import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class OrderProcessor implements IOrderProcessor {
    private final long updateTime = 5000;  // 5 seconds.

    private final Random random = new Random();
    private final OrderRepository orderRepository;

    public OrderProcessor(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Scheduled(fixedRate = updateTime)
    public void processOrders() {
        List<OrderModel> orderModels = orderRepository.findAllByStatus(Status.CHECK);
        for (OrderModel orderModel : orderModels) {
            Status oldStatus = orderModel.getStatus();
            Status newStatus = random.nextBoolean() ? Status.SUCCESS : Status.REJECTION;

            orderModel.setStatus(newStatus);
            orderRepository.save(orderModel);

            log.info("Order ID={}: Status changed from {} to {}.", orderModel.getId(), oldStatus, newStatus);
        }
    }
}
