package lt.techin.demo.services;

import lt.techin.demo.dto.UserCreationDTO;
import lt.techin.demo.model.User;
import lt.techin.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(UserCreationDTO userCreationDTO) {
        User user = new User();
        user.setUsername(userCreationDTO.getUsername());
        user.setFirstName(userCreationDTO.getFirstName());
        user.setLastName(userCreationDTO.getLastName());
        user.setEmail(userCreationDTO.getEmail());
        userRepository.save(user);
        return user;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(long id, User user) {
        User updatedUser = userRepository.findById(id).orElse(null);
        if (updatedUser.getUsername() != null) {
            updatedUser.setUsername(user.getUsername());
        }
        if (updatedUser.getFirstName() != null) {
            updatedUser.setFirstName(user.getFirstName());
        }
        if (updatedUser.getLastName() != null) {
            updatedUser.setLastName(user.getLastName());
        }
        if (updatedUser.getEmail() != null) {
            updatedUser.setEmail(user.getEmail());
        }
        userRepository.save(updatedUser);
        return updatedUser;
    }
}
