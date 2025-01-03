package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.entities.Weekday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface WeekdayRepository extends JpaRepository<Weekday, Short> {

    @Query(
            nativeQuery = true,
            value = "select * " +
                    "from weekday"
    )
    List<Weekday> listAll();

    @Query(
            nativeQuery = true,
            value = "select * " +
                    "from weekday " +
                    "where day_id=?1"
    )
    Optional<Weekday> findWeekdayById(@NonNull Short id);

    @Query(
            nativeQuery = true,
            value = "select * " +
                    "from weekday " +
                    "where day_name like ?1"
    )
    Optional<Weekday> findWeekdayByName(@NonNull String dayName);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into weekday(day_id,day_name) " +
                    "values (?1,?2)"
    )
    Integer create(@NonNull String dayName);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update weekday " +
                    "set day_name=?2 " +
                    "where day_id=?1"
    )
    Integer edit(@NonNull Short id, @NonNull String dayName);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from weekday " +
                    "where day_id=?1"
    )
    void deleteById(@NonNull Short id);
}
