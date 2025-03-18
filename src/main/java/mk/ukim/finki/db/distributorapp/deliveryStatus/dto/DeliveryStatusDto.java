package mk.ukim.finki.db.distributorapp.deliveryStatus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryStatusDto {
    private Short id;
    private String statusName;
    private String statusDescription;
}
