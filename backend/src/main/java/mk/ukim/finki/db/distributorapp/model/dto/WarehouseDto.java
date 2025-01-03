package mk.ukim.finki.db.distributorapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WarehouseDto {
    private Integer id;
    private String address;
    private Long cityId;
    private String cityName;
    private Integer regionId;
    private String regionName;
}
