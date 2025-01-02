package mk.ukim.finki.db.distributorapp.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("categoryId")
    private Long categoryId;

    @Column(name = "ctg_name", nullable = false)
    @JsonProperty("categoryName")
    private String categoryName;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Article> articles;


}
