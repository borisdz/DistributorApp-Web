package mk.ukim.finki.db.distributorapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("categoryId")
    private Long categoryId;

    @Column(name = "ctg_name", nullable = false)
    @JsonProperty("categoryName")
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Article> articles;


}
