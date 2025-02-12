package mk.ukim.finki.db.distributorapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliverySimpleDto {
    private Long deliveryId;
    private String driverName;
    private String vehiclePlate;
    private Short delStatusId;
    private String delStatusName;
}
