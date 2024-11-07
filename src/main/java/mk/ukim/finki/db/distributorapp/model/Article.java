package mk.ukim.finki.db.distributorapp.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.db.distributorapp.model.junctions.WarehouseArticle;

import java.util.List;

@Entity
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    private String article_name;
    private Double article_weight;
    private Double price;

    @ManyToMany(mappedBy = "articles")
    private List<Order> orders;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WarehouseArticle> warehouseArticles;
}
