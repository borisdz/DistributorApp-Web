package mk.ukim.finki.db.distributorapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProFormaDto {
    private Long id;
    private LocalDate pfDeadline;
    private LocalDate pfDateCreated;
    private Short statusId;
    private String statusName;
    private Long ordId;
    private Long customerId;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
}
