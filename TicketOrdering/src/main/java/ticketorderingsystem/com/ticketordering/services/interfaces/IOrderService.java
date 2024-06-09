package ticketorderingsystem.com.ticketordering.services.interfaces;

import ticketorderingsystem.com.ticketordering.models.OrderModel;

public interface IOrderService {
    OrderModel createOrder(long fromStationId, long toStationId, long userId);

    OrderModel getOrder(long orderId);
}
