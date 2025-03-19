package mk.ukim.finki.db.distributorapp.delivery.dto;

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
    private Integer vehId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date delDate;
    private List<Long> orders;
}
