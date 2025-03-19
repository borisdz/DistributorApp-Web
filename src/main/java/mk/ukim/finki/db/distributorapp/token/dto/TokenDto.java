package mk.ukim.finki.db.distributorapp.token.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    Long t_id;
    String t_value;
    LocalDateTime t_date;
    String t_type;
    LocalDateTime t_expiry;
    LocalDateTime t_validated_at;
    Long user_id;
}
