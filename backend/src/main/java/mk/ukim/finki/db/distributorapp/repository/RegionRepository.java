package mk.ukim.finki.db.distributorapp.repository;

import mk.ukim.finki.db.distributorapp.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
