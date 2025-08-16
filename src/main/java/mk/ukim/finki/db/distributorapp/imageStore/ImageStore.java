package mk.ukim.finki.db.distributorapp.imageStore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "image_store")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "img_path", nullable = false)
    private String imgPath;

    @Column(name = "img_ent_type", nullable = false)
    private String imgEntType;

    @Column(name = "img_ent_id", nullable = false)
    private Long imgEntId;

}
