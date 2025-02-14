package mk.ukim.finki.db.distributorapp.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryEndDto {
    private Long id;
    private Integer delEndKm;
}
