package mk.ukim.finki.db.distributorapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class DeliveryDto {
    private Long id;
    private LocalDate dateCreated;
    private LocalDate delDate;
    private Integer delStartKm;
    private Integer delEndKm;
    private LocalTime delStartTime;
    private LocalTime delEndTime;
    private Short dStatusId;
    private String delStatus;
    private Integer vehId;
    private Long driverId;
    private String driverName;
    private String driverImg;
}
