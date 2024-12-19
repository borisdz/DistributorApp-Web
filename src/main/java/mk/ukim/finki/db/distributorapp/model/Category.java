package mk.ukim.finki.db.distributorapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "Category")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ctg_id")
    private Long category_id;

    @Column(name = "ctg_name", nullable = false)
    private String category_name;

    @OneToMany(mappedBy = "category")
    private List<Article> articles;


}
