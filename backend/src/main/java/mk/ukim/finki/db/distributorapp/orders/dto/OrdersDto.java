package mk.ukim.finki.db.distributorapp.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDto {
    private Long id;
    private LocalDate ordDate;
    private Integer ordSum;
    private LocalDateTime ordFulfillmentDate;
    private String ordComment;
    private Short oStatusId;
    private String statusName;
    private Long customerId;
    private String customerName;
    private String customerPhone;
    private String customerEmail;
    private Long deliveryId;
    private Long driverId;
    private String driverName;
    private String driverPhone;
    private String driverEmail;
    private Long pfId;
    private String pfStatus;
}
