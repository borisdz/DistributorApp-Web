package mk.ukim.finki.db.distributorapp.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrdersDeliveryDto {
    private Long id;
    private java.sql.Date ordDate;
    private Integer ordSum;
    private LocalDateTime ordFulfillmentDate;
    private String ordComment;
    private Short oStatusId;
    private Long customerId;
    private Long deliveryId;
    private Double latitude;
    private Double longitude;
}
