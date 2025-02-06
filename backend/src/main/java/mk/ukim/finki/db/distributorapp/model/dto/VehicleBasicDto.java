package mk.ukim.finki.db.distributorapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleBasicDto {
    private Integer id;
    private Integer warehouseId;
    private String plateNumber;
}
