package mk.ukim.finki.db.distributorapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDto {
    private Long id;
    private LocalDate dateCreated;
    private LocalDate deliveryDate;
    private Integer delStartKm;
    private Integer delEndKm;
    private LocalTime delStartTime;
    private LocalTime deliveryEndTime;
    private Short dStatusId;
    private String delStatus;
    private Integer vehId;
    private Long driverId;
    private String driverName;
    private String driverImg;
}
