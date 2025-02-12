package mk.ukim.finki.db.distributorapp.users;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.users.dto.UserDto;
import mk.ukim.finki.db.distributorapp.security.dto.UsersLoadingDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query(
            nativeQuery = true,
            value = """
                    select *
                    from users u
                    left join customer c on u.user_id = c.user_id
                    left join driver d on u.user_id = d.user_id
                    left join manager m on u.user_id = m.user_id
                    order by u.user_id
                    """
    )
    List<Users> listAll();

    @Query(
            nativeQuery = true,
            value = """
                    select *
                    from users u
                    left join customer c on u.user_id = c.user_id
                    left join driver d on u.user_id = d.user_id
                    left join manager m on u.user_id = m.user_id
                    where u.user_name like :name
                    """
    )
    List<Users> findAllByName(@NonNull @Param("name") String name);

    @Query(
            nativeQuery = true,
            value = """
                    select *
                    from users u
                    left join customer c on u.user_id = c.user_id
                    left join driver d on u.user_id = d.user_id
                    left join manager m on u.user_id = m.user_id
                    where u.user_id=:id
                    """
    )
    Optional<Users> findById(@NonNull @Param("id") Long id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into users(user_name,user_surname,user_pass,user_salt,user_email,user_mobile,user_active,user_image,city_id,user_role,user_rtoken,user_rtoken_exp,clazz_) " +
                    "values (:name,:sur,:pass,:salt,:email,:mob,:active,:img,:cty,:role,:rtoken,:rtoken_exp,:clazz_)"
    )
    Integer create(
            @NonNull @Param("name") String name,
            @NonNull @Param("sur") String surname,
            @NonNull @Param("pass") String password,
            @NonNull @Param("email") String email,
            @NonNull @Param("mob") String mobile,
            @NonNull @Param("salt") String salt,
            @NonNull @Param("active") Boolean active,
            @Param("img") String image,
            @NonNull @Param("cty") Integer city_id,
            @NonNull @Param("role") String role,
            @Param("rtoken") String rtoken,
            @Param("rtoken_exp") LocalDateTime rtoken_exp,
            @NonNull @Param("clazz_") String clazz_);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update users " +
                    "set user_name=:name,user_surname=:sur,user_pass=:pass,user_email=:email,user_mobile=:mob,user_salt=:salt,user_active=:active,user_image=:img,city_id=:cty,user_role=:role,user_rtoken=:rtoken,user_rtoken_exp=:rtoken_exp,clazz_=:clazz_ " +
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
            @NonNull @Param("cty") Integer city_id,
            @NonNull @Param("role") String role,
            @NonNull @Param("clazz_") String clazz_);

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
            value = """
                    select *
                    from users u
                        left join customer c on u.user_id = c.user_id
                        left join driver d on u.user_id = d.user_id
                        left join manager m on u.user_id = m.user_id
                    where user_email like :email
                    """
    )
    Optional<Users> findUserByUserEmailIgnoreCase(@NonNull @Param("email") String email);

    @Query(
            nativeQuery = true,
            value = """
                    select *
                    from users
                    where user_email:email and user_pass=:pass
                    """
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

    @Query(
            nativeQuery = true,
            value = """
                    select * from users where user_rtoken=:token
                    """
    )
    Optional<Users> findUserByResetToken(@NonNull @Param("token") String token);

    Optional<Users> findUsersByUserEmailIgnoreCase(String email);

    @Query(
            nativeQuery = true,
            value = """
                    select user_id as userId,
                           user_name as userName,
                           user_surname as userSurname,
                           user_pass as userPassword,
                           user_email as userEmail,
                           user_mobile as userMobile,
                           user_salt as userSalt,
                           user_active as userActive,
                           user_image as userImage,
                           user_role as userRole,
                           clazz_
                    from users
                    where user_email = ?1
                    """
    )
    UsersLoadingDto findUsersByUserEmailIgnoreCaseDto(String email);

    @Query(
            nativeQuery = true,
            value = """
                    select u.user_id as id,
                           u.user_name as firstName,
                           u.user_surname as lastName,
                           u.user_email as email,
                           u.user_mobile as phone,
                           u.user_image as image,
                           u.city_id as cityId,
                           c.city_name as cityName,
                           r.region_name as regionName,
                           u.user_role as role,
                           clazz_ as clazz_,
                           u.user_active as userActive
                    from users u
                    join city c on u.city_id = c.city_id
                    join region r on c.region_id = r.region_id
                    where user_email = ?1
                    """
    )
    UserDto findUserDtoByEmail(String userEmail);
}