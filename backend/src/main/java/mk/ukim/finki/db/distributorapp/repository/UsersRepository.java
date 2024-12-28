package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query(
            nativeQuery = true,
            value = ""
    )
    List<Users> listAll();

    @Query(
            nativeQuery = true,
            value = ""
    )
    List<Users> findAllByName(@NonNull @Param("name") String name);

    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<Users> findById(@NonNull @Param("id") Short id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    Users create(String name, String surname, String password, String email,
                 String mobile, String salt, Boolean active, String image, Long city_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<Users> edit();

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    void delete();

    ////////////////////////////////////////////////
    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<Users> findUserByUserEmailIgnoreCase(@NonNull @Param("email") String email);

    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<Users> findUserByUserNameAndUserPassword(String pass, String username);

    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<Users> findUserByUserName(String username);
}