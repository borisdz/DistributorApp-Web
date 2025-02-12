package mk.ukim.finki.db.distributorapp.vehicle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class VehicleDto {
    private Integer id;
    private Integer carryWeight;
    private Short serviceInterval;
    private Integer kilometers;
    private Date lastServiceDate;
    private Integer lastServiceKm;
    private String plate;
    private String vin;
    private Date registrationDate;
    private Integer whId;
    private String city;
    private String region;
    private Long driverId;
    private String driverName;
    private String driverEmail;
    private String driverPhone;
    private String driverImg;
}
