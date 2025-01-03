package mk.ukim.finki.db.distributorapp.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "Category")
@Data
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ctg_id")
    private Long categoryId;

    @Column(name = "ctg_name", nullable = false)
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Article> articles;


}
