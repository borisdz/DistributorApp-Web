package mk.ukim.finki.db.distributorapp.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseDto {
    private Integer id;
    private String address;
    private Integer cityId;
    private String cityName;
    private Integer regionId;
    private String regionName;
}
