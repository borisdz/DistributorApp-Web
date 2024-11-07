package mk.ukim.finki.db.distributorapp.model.junctions;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.db.distributorapp.model.Article;
import mk.ukim.finki.db.distributorapp.model.Warehouse;

@Data
@Entity
public class WarehouseArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseArticleId;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    private int quantity;
}
