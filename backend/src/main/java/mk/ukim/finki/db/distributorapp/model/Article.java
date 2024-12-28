package mk.ukim.finki.db.distributorapp.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "article", schema = "IND0_185022")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "art_id", nullable = false)
    private Long articleId;

    @Column(name = "art_name", nullable = false)
    private String articleName;

    @Column(name = "art_weight", nullable = false)
    private Integer articleWeight;

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
