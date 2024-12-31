package mk.ukim.finki.db.distributorapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "region", schema = "IND0_185022")
public class Region {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "region_id", nullable = false)
  @JsonProperty("regionId")
  private Integer regionId;

  @Column(name = "region_name", nullable = false)
  @JsonProperty("regionName")
  private String regionName;

  @OneToMany(mappedBy = "region")
  private List<City> cities;

}
