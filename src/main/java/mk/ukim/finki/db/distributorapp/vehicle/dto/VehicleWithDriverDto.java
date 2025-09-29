package mk.ukim.finki.db.distributorapp.vehicle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.db.distributorapp.driver.dto.DriverDto;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleWithDriverDto {
    private Integer id;
    private Integer carryWeight;
    private Short serviceInterval;
    private Integer kilometers;
    private Date lastServiceDate;
    private Integer lastServiceKm;
    private String plate;
    private String vin;
    private Date registrationDate;

    DriverDto driver;
}
