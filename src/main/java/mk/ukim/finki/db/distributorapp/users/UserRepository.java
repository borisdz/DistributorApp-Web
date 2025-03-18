package mk.ukim.finki.db.distributorapp.users;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.users.dto.UserDto;
import mk.ukim.finki.db.distributorapp.users.dto.UsersLoadingDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into users(user_name,user_surname,user_pass,user_salt,user_email,user_mobile,user_active,user_image,city_id,user_role,clazz_) " +
                    "values (:name,:sur,:pass,:salt,:email,:mob,:active,:img,:cty,:role,:clazz_)"
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
            @NonNull @Param("clazz_") String clazz_);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update users " +
                    "set user_name=:name,user_surname=:sur,user_pass=:pass,user_email=:email,user_mobile=:mob,user_salt=:salt,user_active=:active,user_image=:img,city_id=:cty,user_role=:role,clazz_=:clazz_ " +
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

    @Query(
            nativeQuery = true,
            value = """
                    select u.user_id as userId,
                           u.user_name as userName,
                           u.user_surname as userSurname,
                           u.user_pass as userPassword,
                           u.user_email as userEmail,
                           u.user_mobile as userMobile,
                           u.user_salt as userSalt,
                           u.user_active as userActive,
                           u.user_image as userImage,
                           u.user_role as userRole,
                           u.clazz_ as clazz_
                    from users u
                    join token t on u.user_id=t.t_user
                    where t.t_value = :token
                    """
    )
    UsersLoadingDto findUserByResetToken(@NonNull @Param("token") String token);
}