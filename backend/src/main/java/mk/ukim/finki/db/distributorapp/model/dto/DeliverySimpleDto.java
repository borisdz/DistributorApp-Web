package mk.ukim.finki.db.distributorapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliverySimpleDto {
    private Long deliveryId;
    private String driverName;
    private Date deliveryDate;
    private Date deliveryCreatedDate;
    private Short deliveryStatus;
    private String deliveryStatusName;
}
