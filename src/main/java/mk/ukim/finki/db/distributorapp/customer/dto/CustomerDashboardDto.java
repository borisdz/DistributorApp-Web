package mk.ukim.finki.db.distributorapp.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliveryDto;
import mk.ukim.finki.db.distributorapp.order.dto.OrderSimpleDto;
import mk.ukim.finki.db.distributorapp.proForma.dto.ProFormaDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDashboardDto {
    List<OrderSimpleDto> orders;
    List<DeliveryDto> deliveries;
    List<ProFormaDto> proFormas;
}
