package mk.ukim.finki.db.distributorapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryCreateDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date delDate;
    Integer vehId;

    List<Long> orders;
}
