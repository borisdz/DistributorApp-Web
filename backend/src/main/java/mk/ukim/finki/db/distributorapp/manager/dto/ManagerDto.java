package mk.ukim.finki.db.distributorapp.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String image;
    private Integer whId;
    private String whRegion;
    private String whCity;
}
