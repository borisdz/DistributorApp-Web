package mk.ukim.finki.db.distributorapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WarehouseInventoryDto {
    String articleName;
    String manufacturerName;
    Integer totalUnits;
}
