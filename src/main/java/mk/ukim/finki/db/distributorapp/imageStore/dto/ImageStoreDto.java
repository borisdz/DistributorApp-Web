package mk.ukim.finki.db.distributorapp.imageStore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageStoreDto {
    private Long id;
    private String imgPath;
    private String imgEntType;
    private String imgEntId;
}
