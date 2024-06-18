package ticketorderingsystem.com.ticketordering.dtos;

import lombok.Builder;
import lombok.Data;
import ticketorderingsystem.com.ticketordering.models.OrderModel;
import ticketorderingsystem.com.ticketordering.models.enums.Status;

import java.util.Date;

@Builder
@Data
public class OrderDTO {
    private long id;
    private long userId;
    private long fromStationId;
    private long toStationId;
    private Status status;
    private Date createdAt;

    public static OrderDTO toDTO(OrderModel orderModel) {
        return OrderDTO.builder()
                .id(orderModel.getId())
                .userId(orderModel.getUserId())
                .fromStationId(orderModel.getFromStation().getId())
                .toStationId(orderModel.getToStation().getId())
                .status(orderModel.getStatus())
                .createdAt(orderModel.getCreatedAt())
                .build();
    }
}
