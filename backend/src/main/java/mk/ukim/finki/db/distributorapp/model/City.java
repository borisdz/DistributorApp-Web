package mk.ukim.finki.db.distributorapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "city", schema = "IND0_185022")
public class City {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "city_id")
  private Long cityId;

  @Column(name = "city_name", nullable = false)
  private String cityName;

  @OneToMany(mappedBy = "city")
  private List<Users> users;

  @OneToMany(mappedBy = "city")
  private List<Warehouse> warehouses;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "region_id", nullable = false)
  private Region region;

}
