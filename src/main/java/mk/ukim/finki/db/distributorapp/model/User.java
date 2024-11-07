package mk.ukim.finki.db.distributorapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long user_id;
    public String user_name;
    public String user_surname;
    public String mobile_no;
    public String email;

    @ManyToOne
    @JoinColumn(name = "city_id",nullable = false)
    public City city;
}
