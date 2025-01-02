package mk.ukim.finki.db.distributorapp.model.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("category")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "man_id", nullable = false)
    @JsonProperty("manufacturer")
    private Manufacturer manufacturer;

    @OneToMany(mappedBy = "article")
    @JsonIgnore
    private List<Price> prices;

    @OneToMany(mappedBy = "article")
    @JsonIgnore
    private List<ArticleUnit> articleUnits;
}
