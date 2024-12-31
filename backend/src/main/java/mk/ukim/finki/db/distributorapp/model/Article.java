package mk.ukim.finki.db.distributorapp.model;


import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("articleId")
    private Long articleId;

    @Column(name = "art_name", nullable = false)
    @JsonProperty("articleName")
    private String articleName;

    @Column(name = "art_weight", nullable = false)
    @JsonProperty("articleWeight")
    private Integer articleWeight;

    @Column(name = "art_image", nullable = false)
    @JsonProperty("articleImage")
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
