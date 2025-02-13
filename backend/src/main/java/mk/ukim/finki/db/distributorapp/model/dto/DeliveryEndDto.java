package mk.ukim.finki.db.distributorapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryEndDto {
    private Long id;
    private Integer delEndKm;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime delEndTime;
}
