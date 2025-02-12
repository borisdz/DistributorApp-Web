package mk.ukim.finki.db.distributorapp.proForma.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProFormaStatusDto {
    private Short id;
    private String statusName;
    private String statusDescription;
}
