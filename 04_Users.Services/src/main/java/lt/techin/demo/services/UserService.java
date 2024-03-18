package lt.techin.demo.services;

import lt.techin.demo.dto.UserCreationDTO;
import lt.techin.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User createUser(UserCreationDTO userCreationDTO);

    User getUserById(Long id);

    void deleteUser(long id);

    User updateUser(long id, User user);
}
