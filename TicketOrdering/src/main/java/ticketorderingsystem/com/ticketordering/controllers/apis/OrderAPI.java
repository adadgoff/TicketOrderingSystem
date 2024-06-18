package ticketorderingsystem.com.ticketordering.controllers.apis;

import jakarta.servlet.http.HttpServletRequest;
import ticketorderingsystem.com.ticketordering.controllers.requests.OrderRequest;
import ticketorderingsystem.com.ticketordering.dtos.OrderDTO;

public interface OrderAPI {
    OrderDTO createOrder(OrderRequest orderRequest, HttpServletRequest request);

    OrderDTO getOrder(long orderId);
}
