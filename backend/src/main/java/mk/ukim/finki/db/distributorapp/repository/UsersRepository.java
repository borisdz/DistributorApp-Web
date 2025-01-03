package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.entities.Users;
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
            value = "select * from users"
    )
    List<Users> listAll();

    @Query(
            nativeQuery = true,
            value = "select * from users where user_name like :name"
    )
    List<Users> findAllByName(@NonNull @Param("name") String name);

    @Query(
            nativeQuery = true,
            value = "select * from users where user_id=:id"
    )
    Optional<Users> findById(@NonNull @Param("id") Short id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into users(user_name, user_surname, user_pass, user_salt, user_email, user_mobile, user_email_conf, user_image, city_id) " +
                    "values (:name,:sur,:pass,:salt,:email,:mob,:active,:img,:cty)"
    )
    Integer create(
            @NonNull @Param("name") String name,
            @NonNull @Param("sur") String surname,
            @NonNull @Param("pass") String password,
            @NonNull @Param("email") String email,
            @NonNull @Param("mob") String mobile,
            @NonNull @Param("salt") String salt,
            @NonNull @Param("active") Boolean active,
            @NonNull @Param("img") String image,
            @NonNull @Param("cty") Long city_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update users " +
                    "set user_name=:name,user_surname=:sur,user_pass=:pass,user_email=:email,user_mobile=:mob,user_salt=:salt,user_email_conf=:active,user_image=:img,city_id=:cty " +
                    "where user_id=:id"
    )
    Integer edit(
            @NonNull @Param("id") Long id,
            @NonNull @Param("name") String name,
            @NonNull @Param("sur") String surname,
            @NonNull @Param("pass") String password,
            @NonNull @Param("email") String email,
            @NonNull @Param("mob") String mobile,
            @NonNull @Param("salt") String salt,
            @NonNull @Param("active") Boolean active,
            @NonNull @Param("img") String image,
            @NonNull @Param("cty") Long city_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from users where user_id=:id"
    )
    void delete(@NonNull @Param("id") Long id);

    ////////////////////////////////////////////////
    // TODO: Update the queries bellow.
    @Query(
            nativeQuery = true,
            value = "select * " +
                    "from users " +
                    "where user_email like :email"
    )
    Optional<Users> findUserByUserEmailIgnoreCase(@NonNull @Param("email") String email);

    @Query(
            nativeQuery = true,
            value = "select * " +
                    "from users " +
                    "where user_email:email and user_pass=:pass"
    )
    Optional<Users> findUserByUserNameAndUserPassword(
            @NonNull @Param("pass") String password,
            @NonNull @Param("email") String email);

    @Query(
            nativeQuery = true,
            value = "select * from users where user_email=:email"
    )
    Optional<Users> findUserByUserName(
            @NonNull @Param("email") String username);
}