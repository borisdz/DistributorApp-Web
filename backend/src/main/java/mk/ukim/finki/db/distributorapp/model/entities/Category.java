package mk.ukim.finki.db.distributorapp.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @Size(max = 255)
    @NotNull
    @Column(name = "ctg_name", nullable = false)
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Article> articles;


}
