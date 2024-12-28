package mk.ukim.finki.db.distributorapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "Category")
@Data
@Table(name = "category", schema = "IND0_185022")
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
