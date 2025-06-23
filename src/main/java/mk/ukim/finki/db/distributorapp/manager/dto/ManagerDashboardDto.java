package mk.ukim.finki.db.distributorapp.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliverySimpleDto;
import mk.ukim.finki.db.distributorapp.order.dto.OrderSimpleDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerDashboardDto {
    private List<OrderSimpleDto> newOrders;
    private List<DeliverySimpleDto> pendingDeliveries;
}
