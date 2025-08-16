package mk.ukim.finki.db.distributorapp.users;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.users.dto.UserDto;
import mk.ukim.finki.db.distributorapp.users.dto.UsersLoadingDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Integer edit(UserDto userDto) {
        UsersLoadingDto user = this.userRepository.findUsersByUserEmailIgnoreCaseDto(userDto.getEmail());
        return this.userRepository.edit(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                user.getUserPassword(),
                userDto.getEmail(),
                userDto.getPhone(),
                user.getUserSalt(),
                userDto.getUserActive(),
                userDto.getImage(),
                userDto.getCityId(),
                userDto.getRole(),
                userDto.getClazz_()
        );
    }

    @Override
    public UsersLoadingDto findUserByResetToken(String token) {
        return this.userRepository.findUserByResetToken(token);
    }

    @Override
    public UserDto findUserDtoByEmail(String userEmail) {
        return this.userRepository.findUserDtoByEmail(userEmail);
    }

    @Override
    public UsersLoadingDto findFullUserDtoByEmail(String userEmail) {
        return this.userRepository.findUsersByUserEmailIgnoreCaseDto(userEmail);
    }

    @Override
    @Transactional
    public void updateUser(Long id,String firstName, String lastName, String email, String phone, String image, Integer cityId) {
        this.userRepository.updateUser(
                id,
                firstName,
                lastName,
                email,
                phone,
                image,
                cityId
        );
    }

    @Override
    @Transactional
    public void updateUserDetails(Long id, String firstName, String lastName, String email, String phone, Integer cityId) {
        this.userRepository.updateUserDetails(
                id,
                firstName,
                lastName,
                email,
                phone,
                cityId
        );
    }

    @Override
    public void deleteUserById(Long id) {
        this.userRepository.delete(id);
    }
}
