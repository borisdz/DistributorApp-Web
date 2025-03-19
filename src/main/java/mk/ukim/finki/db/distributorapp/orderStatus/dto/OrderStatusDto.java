package mk.ukim.finki.db.distributorapp.orderStatus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderStatusDto {
    private Short id;
    private String statusName;
    private String statusDescription;
}
