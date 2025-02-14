package mk.ukim.finki.db.distributorapp.delivery.dto;

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

    private String delStartTime;
    private String delEndTime;

    private Short dStatusId;
    private String delStatus;
    private Integer vehId;
    private Long driverId;
    private String driverName;
    private String driverImg;


    public LocalTime getParsedDelStartTime() {
        return delStartTime != null ? LocalTime.parse(delStartTime) : null;
    }

    public LocalTime getParsedDelEndTime() {
        return delEndTime != null ? LocalTime.parse(delEndTime) : null;
    }
}
