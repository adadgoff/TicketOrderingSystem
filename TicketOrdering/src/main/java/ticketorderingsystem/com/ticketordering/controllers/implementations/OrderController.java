package ticketorderingsystem.com.ticketordering.controllers.implementations;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import ticketorderingsystem.com.ticketordering.controllers.apis.OrderAPI;
import ticketorderingsystem.com.ticketordering.controllers.requests.OrderRequest;
import ticketorderingsystem.com.ticketordering.dtos.OrderDTO;
import ticketorderingsystem.com.ticketordering.models.OrderModel;
import ticketorderingsystem.com.ticketordering.services.implementations.OrderService;
import ticketorderingsystem.com.ticketordering.services.implementations.TokenService;

@RestController
@RequestMapping("/order")
public class OrderController implements OrderAPI {
    private final OrderService orderService;
    private final TokenService tokenService;

    public OrderController(OrderService orderService, TokenService tokenService) {
        this.orderService = orderService;
        this.tokenService = tokenService;
    }

    @Override
    @PostMapping("/create")
    public OrderDTO createOrder(@RequestBody OrderRequest orderRequest, HttpServletRequest request) {
        Long userId = tokenService.authenticateUser(request);
        OrderModel orderModel = orderService.createOrder(orderRequest.fromStationId, orderRequest.toStationId, userId);
        return OrderDTO.toDTO(orderModel);
    }

    @Override
    @GetMapping("/{orderId}")
    public OrderDTO getOrder(@PathVariable long orderId) {
        OrderModel orderModel = orderService.getOrder(orderId);
        return OrderDTO.toDTO(orderModel);
    }
}
