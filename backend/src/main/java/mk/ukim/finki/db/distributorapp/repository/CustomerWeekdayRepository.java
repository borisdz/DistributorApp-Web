package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.entities.CustomerWeekday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

public interface CustomerWeekdayRepository extends JpaRepository<CustomerWeekday, Long> {

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into customer_weekday(cust_id, day_id, start_time, end_time) " +
                    "values (?1,?2,?3,?4)"
    )
    Integer create(
            Long userId,
            Short weekdayId,
            LocalTime startTime,
            LocalTime endTime);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update customer_weekday " +
                    "set cust_id=?2,day_id=?3,start_time=?4,end_time=?5 " +
                    "where cust_day_id=?1"
    )
    Integer edit(
            Long custDayId,
            Long userId,
            Short weekdayId,
            LocalTime startTime,
            LocalTime endTime);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from customer_weekday where cust_day_id=?1"
    )
    void deleteById(@NonNull Long id);
}
