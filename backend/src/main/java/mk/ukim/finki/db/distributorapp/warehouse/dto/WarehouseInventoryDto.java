package mk.ukim.finki.db.distributorapp.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WarehouseInventoryDto {
    String articleName;
    String manufacturerName;
    Long totalUnits;
}
