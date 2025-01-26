package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.dto.UserDto;
import mk.ukim.finki.db.distributorapp.model.entities.Users;
import mk.ukim.finki.db.distributorapp.repository.UsersRepository;
import mk.ukim.finki.db.distributorapp.service.UsersService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<Users> findAllUsers() {
        return this.usersRepository.listAll();
    }

    @Override
    public Users findUserById(Long id) {
        return this.usersRepository.findById(id).get();
    }

    @Override
    public Users findUserByEmail(String email) {
        return this.usersRepository.findUserByUserEmailIgnoreCase(email).get();
    }

    @Override
    public Integer edit(UserDto userDto) {
        Users user = this.usersRepository.findUserByUserEmailIgnoreCase(userDto.getEmail()).get();
        return this.usersRepository.edit(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                user.getUserPassword(),
                userDto.getEmail(),
                userDto.getPhone(),
                user.getUserSalt(),
                user.getUserActive(),
                userDto.getImage(),
                
        );
    }

    @Override
    public Integer deleteUserById(Long id) {
        return 0;
    }
}
