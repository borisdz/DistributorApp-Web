package mk.ukim.finki.db.distributorapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
public class DeliveryDto {
    private Long id;
    private Date dateCreated;
    private Date delDate;
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
