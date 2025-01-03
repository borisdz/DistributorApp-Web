package mk.ukim.finki.db.distributorapp.model.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "art_id", nullable = false)
    private Long articleId;

    @Column(name = "art_name", nullable = false)
    private String articleName;

    @Column(name = "art_weight", nullable = false)
    private Integer articleWeight;

    @Column(name = "art_image", nullable = false)
    private String artImage;

    @ManyToOne
    @JoinColumn(name = "ctg_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "man_id", nullable = false)
    private Manufacturer manufacturer;

    @OneToMany(mappedBy = "article")
    private List<Price> prices;

    @OneToMany(mappedBy = "article")
    private List<ArticleUnit> articleUnits;
}
