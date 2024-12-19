package mk.ukim.finki.db.distributorapp.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "art_id")
    private Long article_id;

    @Column(name = "art_name", nullable = false)
    private String article_name;

    @Column(name = "art_weight", nullable = false)
    private Integer article_weight;

    @ManyToOne
    @JoinColumn(name = "ctg_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "man_id", nullable = false)
    private Manufacturer manufacturer;

    @OneToMany(mappedBy = "article")
    private List<Price> prices;

    @OneToMany(mappedBy = "article")
    private List<Article_Unit> articleUnits;
}
