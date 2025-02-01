package mk.ukim.finki.db.distributorapp.model.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @Size(max = 255)
    @NotNull
    @Column(name = "art_name", nullable = false)
    private String articleName;

    @NotNull
    @Column(name = "art_weight", nullable = false)
    private Integer articleWeight;

    @Size(max = 255)
    @NotNull
    @Column(name = "art_image", nullable = false)
    private String artImage;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "ctg_id", nullable = false)
    private Category category;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "man_id", nullable = false)
    private Manufacturer manufacturer;

    @OneToMany(mappedBy = "article")
    private List<Price> prices;

    @OneToMany(mappedBy = "article")
    private List<ArticleUnit> articleUnits;
}
