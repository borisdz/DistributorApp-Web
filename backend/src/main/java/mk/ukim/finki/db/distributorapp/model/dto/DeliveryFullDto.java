package mk.ukim.finki.db.distributorapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryFullDto {
    private Long delId;
    private Date delDateCreated;
    private Date delDate;
    private Integer delStartKm;
    private Integer delEndKm;

    private String delStartTime;
    private String delEndTime;

    private Short delStatusId;
    private Integer veh_id;

    public LocalTime getParsedDelStartTime() {
        return delStartTime != null ? LocalTime.parse(delStartTime) : null;
    }

    public LocalTime getParsedDelEndTime() {
        return delEndTime != null ? LocalTime.parse(delEndTime) : null;
    }
}
